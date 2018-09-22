package net.grian.commons.netty;

import io.netty.util.collection.IntObjectHashMap;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("unchecked")
public abstract class AbstractPacketHandler {

    @Getter
    @Setter
    protected Connection connection;

    protected IntObjectHashMap<PacketHandle> handleMap = new IntObjectHashMap<>();

    public void addHandles(PacketHandle... handles) {
        for (PacketHandle handle : handles) {
            handleMap.put(handle.getId(), handle);
        }
    }

    public void handle(Packet packet) {
        handleMap.get(packet.packetId()).handle(packet);
    }

}
