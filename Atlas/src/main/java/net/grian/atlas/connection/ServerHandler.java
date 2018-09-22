package net.grian.atlas.connection;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Setter;
import net.grian.commons.netty.Connection;
import net.grian.commons.netty.Packet;

public class ServerHandler extends SimpleChannelInboundHandler<Packet> {

    @Setter
    private Connection connection;

    public ServerHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        connection.connected();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        connection.disconnected();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) {
        connection.handle(packet);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

}
