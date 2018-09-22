package net.grian.commons.netty.packet.request;

import net.grian.commons.Protocol;
import net.grian.commons.netty.Packet;

public class AtlasResponse implements Packet {

    @Override
    public int packetId() {
        return Protocol.Packet.Response;
    }

}
