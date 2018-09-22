package net.grian.commons.netty.packet.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.grian.commons.Protocol;
import net.grian.commons.netty.Packet;
import net.grian.commons.netty.packet.PacketInfo;

@AllArgsConstructor
@PacketInfo(Protocol.Packet.Debug)
public class Debug implements Packet {

    @Getter
    private String test;

    @Override
    public int packetId() {
        return Protocol.Packet.Debug;
    }
}
