package net.grian.atlas.session;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

@Data
public class PlayerSession {

    private final String sessionId = RandomStringUtils.random(12, true, true);

    private final UUID uuid;
    private final String username;
    private String currentServer;
    private String currentProxy;

    //TODO implement party object
    private Object party;



}
