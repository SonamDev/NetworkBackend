package net.grian.atlas.connection;

import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;
import net.grian.atlas.connection.handle.NotAssignedHandler;
import net.grian.commons.netty.AbstractPacketHandler;
import net.grian.commons.netty.Connection;
import net.grian.commons.netty.Packet;
import net.grian.commons.netty.packet.main.RegisterRequest;
import org.apache.commons.lang3.RandomStringUtils;

import java.net.InetSocketAddress;

public class ServerConnection implements Connection {

    @Getter @Setter
    private int type;

    @Getter
    private Channel channel;

    @Getter
    @Setter
    private String name;

    @Setter
    private AbstractPacketHandler packetAdapter;

    public ServerConnection(Channel channel) {
        this.channel = channel;
        this.name = RandomStringUtils.random(8, true, true) + "@" + ((InetSocketAddress) channel.remoteAddress()).getAddress().getHostAddress();
        this.packetAdapter = new NotAssignedHandler(this);
    }

    @Override
    public void connected() {

    }

    public void setInfo(RegisterRequest.InstanceInfo info) {
        this.type = info.getConnectionType();
        this.name = info.getName();
    }

    @Override
    public void handle(Packet packet) {
        packetAdapter.handle(packet);
    }

    @Override
    public void write(Packet packet) {
        channel.writeAndFlush(packet, channel.voidPromise());
    }

    @Override
    public void write(Packet... packets) {
        for (Packet packet : packets) {
            channel.writeAndFlush(packet, channel.voidPromise());
        }
    }

    @Override
    public void disconnected() {

    }

}
