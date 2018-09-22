package net.grian.hook;

import net.grian.atlas.Atlas;
import net.grian.atlas.ClientConnection;
import net.grian.commons.Global;
import org.bukkit.plugin.java.JavaPlugin;

public class AtlasHook extends JavaPlugin {

    @Override
    public void onDisable() {
        ClientConnection.setHandleSupplier(HerculesAtlasHandler::new);

        Atlas client = new Atlas.Builder()
                .port(35565)
                .hostname("localhost")
                .connectionType(Global.ConnectionType.Spigot)
                .instanceName("INSTANCE1")
                .build();

        client.startClient();
    }

    @Override
    public void onEnable() {

    }

}
