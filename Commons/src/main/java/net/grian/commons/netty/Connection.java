package net.grian.commons.netty;

public interface Connection {

    void handle(Packet packet);

    void write(Packet packet);

    void write(Packet... packets);

    void disconnected();

    void connected();

}
