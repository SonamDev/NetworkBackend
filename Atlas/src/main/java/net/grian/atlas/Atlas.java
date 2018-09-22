package net.grian.atlas;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.grian.atlas.connection.ServerConnection;
import net.grian.atlas.connection.ServerHandler;
import net.grian.commons.Global;
import net.grian.commons.netty.PipelineUtils;
import net.grian.commons.netty.data.AtlasDecoder;
import net.grian.commons.netty.data.AtlasEncoder;
import org.fusesource.jansi.AnsiConsole;

import java.io.PrintStream;

@Accessors(fluent = true)
public class Atlas {

    @Getter
    private static Atlas instance;

    public Atlas(int port) {
        this.port = port;
    }

    private int port;


    public void start() {
        instance = this;

        AnsiConsole.systemInstall();

        System.setOut(new PrintStream(System.out) {
            @Override
            public void println(Object x) {
                super.println(x + "\u001b[0m");
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

        }));

        bootServer();
    }

    private void bootServer() {

        EventLoopGroup bossGroup = new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat( "Atlas NIO Boss Group" ).build());
        EventLoopGroup workerGroup = new NioEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat( "Atlas NIO Worker Group" ).build());

        GenericFutureListener<? extends Future<? super Void>> listener = future -> {
            if(future.isSuccess()) {
                System.out.println("Atlas listening on port " + port);
            }
        };

        new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.ALLOCATOR, ByteBufAllocator.DEFAULT)
                .childOption(ChannelOption.WRITE_BUFFER_WATER_MARK, new WriteBufferWaterMark(8 * 1024, 32 * 1024))
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline()
                                .addLast("compressDecoder", ZlibCodecFactory.newZlibDecoder())
                                .addLast("compressEncoder", ZlibCodecFactory.newZlibEncoder(3))
                                .addLast(Global.Pipeline.FrameDecoderKey, PipelineUtils.getNewDecoder())
                                .addLast(Global.Pipeline.FramePrependerKey, PipelineUtils.GLOBAL_PREPENDER)
                                .addLast(Global.Pipeline.BaseHandlerKey, new ServerHandler(new ServerConnection(ch)))
                                .addAfter(Global.Pipeline.FramePrependerKey, Global.Pipeline.AtlasEncoderKey, new AtlasEncoder())
                                .addAfter(Global.Pipeline.FrameDecoderKey, Global.Pipeline.AtlasDecoderKey, new AtlasDecoder());
                    }

                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                        cause.printStackTrace();
                    }
                }).bind(port).addListener(listener);
    }

}
