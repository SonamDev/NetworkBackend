package net.grian.atlas;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.grian.commons.Global;
import net.grian.commons.netty.PipelineUtils;
import net.grian.commons.netty.data.AtlasDecoder;
import net.grian.commons.netty.data.AtlasEncoder;
import net.grian.commons.netty.packet.main.RegisterRequest;

@Accessors(fluent = true)
public class Atlas {

    private static Atlas instance;

    @Getter
    private String hostname;
    @Getter
    private int port;
    @Getter
    private ClientConnection connection;
    @Getter
    private RegisterRequest.InstanceInfo info;

    private Atlas(String hostname, int port, RegisterRequest.InstanceInfo info) {
        instance = this;
        this.hostname = hostname;
        this.port = port;
        this.info = info;
    }

    public static boolean validateConfig() {
        return false;
    }

    public static Builder newBuilder(byte connectionType) {
        return new Builder(connectionType);
    }

    public static class Builder {

        private Builder() {}

        private Builder(byte connectionType) {
            this.info.setConnectionType(connectionType);
        }

        private String hostname;
        private boolean auto_assign;
        private int port = -1;
        private RegisterRequest.InstanceInfo info = new RegisterRequest.InstanceInfo();

        public Builder hostname(String hostname) {
            this.hostname = hostname;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder autoAssignName() {
            if(this.info.getConnectionType() != Global.ConnectionType.Spigot)
                throw new IllegalArgumentException("Only Spigot instances can be automatically assigned a name");
            this.auto_assign = true;
            return this;
        }

        public Builder instanceName(String name) {
            this.info.setName(name);
            return this;
        }

        public Builder setGameType(String serverType, String gameType) {
            this.info.setServerType(serverType);
            this.info.setGameType(gameType);
            return this;
        }

        public Atlas build() {

            if(auto_assign)
                info.setName("%_automatic_%");

            if(info.getServerType() == null || info.getGameType() == null)
                throw new IllegalArgumentException("Configuration MUST have a server type and game type");

            return new Atlas(this.hostname == null ? "localhost" : this.hostname,
                    this.port == -1 ? 35565 : this.port, this.info);
        }
    }

    public void startClient() {
        doConnect().connect(hostname, port)
        .addListener(f -> {
            if(f.isSuccess())
                System.out.println("Connected!");
        });
    }

    public static Atlas instance() {
        return instance;
    }

    Bootstrap doConnect() {
        return new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline()
                                .addFirst("reconnect", new ReconnectionHandler())
                                .addLast("compressDecoder", ZlibCodecFactory.newZlibDecoder())
                                .addLast("compressEncoder", ZlibCodecFactory.newZlibEncoder(3))
                                .addLast(Global.Pipeline.FrameDecoderKey, PipelineUtils.getNewDecoder())
                                .addLast(Global.Pipeline.FramePrependerKey, PipelineUtils.GLOBAL_PREPENDER)
                                .addLast(Global.Pipeline.BaseHandlerKey, new ClientHandler(connection = new ClientConnection(sc)))
                                .addAfter(Global.Pipeline.FramePrependerKey, Global.Pipeline.AtlasEncoderKey, new AtlasEncoder())
                                .addAfter(Global.Pipeline.FrameDecoderKey, Global.Pipeline.AtlasDecoderKey, new AtlasDecoder());
                    }
                });
    }


}
