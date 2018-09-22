package net.grian.commons;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import net.grian.commons.netty.PacketRegistry;
import net.grian.commons.netty.packet.main.InvalidPacket;

import java.io.IOException;

/**
 * @author Sonam
 * All Packet IDs
 */

public class Protocol {

    public static final class Packet {
        public static final int Invalid = -1;
        public static final int AuthRequest = 0;
        public static final int AuthResponse = 1;
        public static final int RegisterRequest = 2;
        public static final int RegisterResponse = 3;
        public static final int Status = 4;
        public static final int Profile = 5;
        public static final int Session = 6;
        public static final int Fetch = 7;
        public static final int Update = 9;
        public static final int Heartbeat = 10;
        public static final int Request = 11;
        public static final int Response = 12;
        public static final int StaffChat = 13;
        public static final int Debug = 14;

        public static net.grian.commons.netty.Packet read(int id, ByteBuf buf) {
            int length = buf.readInt();
            Class<? extends net.grian.commons.netty.Packet> clazz = PacketRegistry.getClass(id);
            try {
                return Global.MAPPER.readValue(buf.readCharSequence(length, CharsetUtil.UTF_8).toString(), clazz);
            } catch (IOException e) {
                Global.Defaults.MainExceptionHandler.call(e);
                return new InvalidPacket();
            }
        }

    }



}
