package net.grian.atlas;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import net.grian.commons.netty.AnsiUtil;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class ReconnectTask implements Runnable, ChannelFutureListener {

    private Channel channel;

    ReconnectTask(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        Atlas.instance().doConnect()
                .connect(Atlas.instance().hostname(), Atlas.instance().port())
        .addListener(this);
    }


    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        if(!future.isSuccess()) {
            System.out.println(AnsiUtil.get(Color.ORANGE) + "Could not connect to Atlas, retrying in " + ReconnectionHandler.getReconnectTime() + " ms");
            channel.eventLoop().schedule(this, ReconnectionHandler.getReconnectTime(), TimeUnit.MILLISECONDS);
        }
    }

}
