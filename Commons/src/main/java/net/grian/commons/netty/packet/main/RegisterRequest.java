package net.grian.commons.netty.packet.main;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.grian.commons.Protocol;
import net.grian.commons.netty.Packet;
import net.grian.commons.netty.packet.PacketInfo;

@PacketInfo(Protocol.Packet.RegisterRequest)
@AllArgsConstructor
public class RegisterRequest implements Packet {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static final class InstanceInfo
    {
        private String name;
        private byte connectionType;

        /**
         * GameType = Game's Codename, such as "SKYWARS" or "UHC"
         */
        private String gameType;

        /**
         * ServerType = The server type of the server, such as "LOBBY" or "GAME"
         */
        private String serverType;
    }

    @Getter
    private InstanceInfo info;

    @Override
    public int packetId() {
        return Protocol.Packet.RegisterRequest;
    }

}
