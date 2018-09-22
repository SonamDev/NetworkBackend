package net.grian.atlas;

import io.netty.channel.Channel;
import lombok.Setter;
import net.grian.commons.Global;
import net.grian.commons.netty.AbstractPacketHandler;
import net.grian.commons.netty.AnsiUtil;
import net.grian.commons.netty.Connection;
import net.grian.commons.netty.Packet;
import net.grian.commons.netty.packet.main.AuthRequest;

import java.awt.*;
import java.util.function.Supplier;

public class ClientConnection implements Connection {

    @Setter
    private static Supplier<? extends AbstractPacketHandler> handleSupplier = null;

    private final Channel channel;

    private AbstractPacketHandler handler = null;

    public ClientConnection(Channel ch) {
        this.channel = ch;
        if(handleSupplier==null) {
            for(int i = 0; i < 5; i++) {
                System.out.println(AnsiUtil.get(new Color(208, 70, 54)) + "SUPPLIER IS NULL, PLEASE SET AN ADAPTER SUPPLIER BEFORE USING CLIENT");
            }
            return;
        }
        this.handler = handleSupplier.get();
        this.handler.setConnection(this);
    }

    @Override
    public void connected() {
        write(new AuthRequest(Global.AUTH_KEY));
    }

    @Override
    public void handle(Packet packet) {
        if(this.handler!=null)
            this.handler.handle(packet);
    }

    @Override
    public void write(Packet packet) {
        channel.writeAndFlush(packet);
    }

    @Override
    public void write(Packet... packets) {
        for (Packet packet : packets) {
            channel.writeAndFlush(packet);
        }
    }

    @Override
    public void disconnected() {

    }

}
