package net.grian.commons.netty;

import lombok.Getter;

public abstract class PacketHandle<T> {

    @Getter
    private final int id;

    public PacketHandle(int id) {
        this.id = id;
    }

    public abstract void handle(T t);

}
