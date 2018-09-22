package net.grian.commons.netty.packet.main;

import lombok.AllArgsConstructor;
import net.grian.commons.Protocol;
import net.grian.commons.netty.Packet;
import net.grian.commons.netty.packet.PacketInfo;

@PacketInfo(Protocol.Packet.Status)
@AllArgsConstructor
public class Status implements Packet {

    private byte status;

    @Override
    public int packetId() {
        return Protocol.Packet.Status;
    }

}
