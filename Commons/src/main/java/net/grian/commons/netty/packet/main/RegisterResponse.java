package net.grian.commons.netty.packet.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.grian.commons.Protocol;
import net.grian.commons.netty.Packet;
import net.grian.commons.netty.packet.PacketInfo;

@PacketInfo(Protocol.Packet.RegisterResponse)
@AllArgsConstructor
public class RegisterResponse implements Packet {

    @Getter
    private boolean successful;
    @Getter
    private String failReason;

    @Override
    public int packetId() {
        return Protocol.Packet.RegisterResponse;
    }

}
