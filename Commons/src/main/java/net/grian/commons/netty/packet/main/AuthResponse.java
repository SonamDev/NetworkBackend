package net.grian.commons.netty.packet.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.grian.commons.Protocol;
import net.grian.commons.netty.Packet;
import net.grian.commons.netty.packet.PacketInfo;

@PacketInfo(Protocol.Packet.AuthResponse)
@AllArgsConstructor
public class AuthResponse implements Packet {

    @Getter
    private boolean success;

    @Override
    public int packetId() {
        return Protocol.Packet.AuthResponse;
    }

}
