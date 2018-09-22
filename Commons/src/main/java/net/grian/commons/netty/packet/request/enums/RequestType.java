package net.grian.commons.netty.packet.request.enums;

public enum RequestType {

    ADD_CREDITS, GET_STATS, SERVERS, ADD_EMERALDS, GET_EMERALDS, SET_STATS, MODIFY_CURRENCY, GET_CURRENCY, GET_PARTY, TOP_STATS, MARKET;

    private static RequestType[] staticValues = values();

    public static RequestType get(int ordinal) {
        return staticValues[ordinal];
    }

}
