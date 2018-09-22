package net.grian.commons.netty.packet.main;

import net.grian.commons.Protocol;
import net.grian.commons.netty.Packet;

public class InvalidPacket implements Packet {

    @Override
    public int packetId() {
        return Protocol.Packet.Invalid;
    }

}
