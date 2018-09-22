package net.grian.commons.netty;

public interface Packet
{

    default void handle(AbstractPacketHandler handler) {
        handler.handle(this);
    }

    int packetId();

}
