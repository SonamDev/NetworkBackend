package net.grian.atlas.api;

public interface Service {

    String name();

    void initialize();

    ServiceSwitch getSwitch();

}
