package net.grian.hercules;

import net.grian.atlas.Atlas;
import net.grian.atlas.ClientConnection;
import net.grian.commons.Global;
import net.grian.hercules.atlas.HerculesAtlasHandler;

public class ClientTest {

    public static void main(String[] args) {

        ClientConnection.setHandleSupplier(HerculesAtlasHandler::new);

        Atlas client = Atlas.newBuilder()
                .port(35565)
                .hostname("localhost")
                .connectionType(Global.ConnectionType.Bungee)
                .instanceName("BUNGEE1")
                .build();

        System.out.println("starting client");
        client.startClient();

    }

}
