package net.grian.commons.netty;

import net.grian.commons.netty.packet.PacketInfo;
import net.grian.commons.netty.packet.main.*;

import java.util.HashMap;

public class PacketRegistry {

    private static final HashMap<Integer, Class<? extends Packet>> class_mapping = new HashMap<>();

    @SafeVarargs
    private static void register(Class<? extends Packet>... classes){
        for (Class<? extends Packet> clazz : classes) {
            PacketInfo info = clazz.getAnnotation(PacketInfo.class);
            class_mapping.put(info.value(), clazz);
        }
    }

    public static Class<? extends Packet> getClass(int type) {
        return class_mapping.get(type);
    }

    static {
        register
        (
                AuthRequest.class,
                AuthResponse.class,
                Profile.class,
                RegisterRequest.class,
                RegisterResponse.class,
                StaffChat.class,
                Status.class,
                Debug.class
        );
    }

}
