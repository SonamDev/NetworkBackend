package net.grian.commons.netty.packet.main;

import lombok.AllArgsConstructor;
import net.grian.commons.Protocol;
import net.grian.commons.netty.Packet;
import net.grian.commons.netty.packet.PacketInfo;

@PacketInfo(Protocol.Packet.StaffChat)
@AllArgsConstructor
public class StaffChat implements Packet {

    private byte flags;
    private String prefix;
    private String message;

    @Override
    public int packetId() {
        return Protocol.Packet.StaffChat;
    }
}
