package net.grian.atlas;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.Getter;
import lombok.Setter;
import net.grian.commons.netty.AnsiUtil;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ReconnectionHandler extends ChannelInboundHandlerAdapter {

    @Getter
    @Setter
    private static int reconnectTime = 5000;


    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        tryReconnect(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if(cause instanceof IOException && cause.getMessage().equalsIgnoreCase("An existing connection was forcibly closed by the remote host")) {
            tryReconnect(ctx);
        }
    }

    private void tryReconnect(ChannelHandlerContext ctx) {
        System.out.println(AnsiUtil.get(Color.ORANGE) + "Lost Connection to Atlas... reconnecting in " + reconnectTime + " ms");
        Channel ch = ctx.channel();

        if(ch.eventLoop().isShuttingDown()) return;

        ctx.channel().eventLoop()
                .schedule(new ReconnectTask(ch), reconnectTime, TimeUnit.MILLISECONDS);
    }
}
