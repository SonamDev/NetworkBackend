package net.grian.commons.netty.packet.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.grian.commons.Protocol;
import net.grian.commons.netty.Packet;
import net.grian.commons.netty.packet.PacketInfo;

@PacketInfo(Protocol.Packet.AuthRequest)
@AllArgsConstructor
public class AuthRequest implements Packet {

    @Getter
    private String key;

    @Override
    public int packetId() {
        return Protocol.Packet.AuthRequest;
    }

}
