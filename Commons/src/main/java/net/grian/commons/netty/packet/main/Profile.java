package net.grian.commons.netty.packet.main;

import lombok.AllArgsConstructor;
import net.grian.commons.Protocol;
import net.grian.commons.netty.Packet;
import net.grian.commons.netty.packet.PacketInfo;
import org.bson.Document;

import java.util.UUID;

@PacketInfo(Protocol.Packet.Profile)
@AllArgsConstructor
public class Profile implements Packet {

    private String server;
    private UUID user;
    private Document profile;

    @Override
    public int packetId() {
        return Protocol.Packet.Profile;
    }
}
