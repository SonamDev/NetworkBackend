package net.grian.atlas;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.grian.commons.netty.Connection;
import net.grian.commons.netty.Packet;

@Accessors(fluent = true)
public class ClientHandler extends ChannelDuplexHandler {

    @Setter
    private Connection connection;

    public ClientHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        connection.connected();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Packet packet = (Packet) msg;
        connection.handle(packet);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

}
