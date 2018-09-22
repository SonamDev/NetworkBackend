package net.grian.hercules;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.grian.atlas.Atlas;
import net.grian.atlas.ClientConnection;
import net.grian.commons.Global;
import net.grian.hercules.atlas.HerculesAtlasHandler;
import net.grian.hercules.util.HerculesConfig;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.*;

@Accessors(fluent = true)
public class Hercules extends Plugin {

    private static Hercules instance;

    @Getter
    private HerculesConfig config;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        HerculesConfig loaded = loadConfig();
        if(loaded!=null) {
            config = loaded;
            ClientConnection.setHandleSupplier(HerculesAtlasHandler::new);

            new Atlas.Builder()
                    .instanceName(config.getStringProperty(HerculesConfig.InstanceName))
                    .hostname(config.getStringProperty(HerculesConfig.AtlasHost))
                    .port(config.getIntProperty(HerculesConfig.AtlasPort))
                    .connectionType(Global.ConnectionType.Bungee)
            .build().startClient();
        }

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static Hercules instance() {
        return instance;
    }

    private HerculesConfig loadConfig() {
        File file = new File("config.json");
        try {
            if(!file.exists()) {
                file.createNewFile();
                InputStream is = this.getResourceAsStream("config.json");
                byte[] buffer = new byte[is.available()];
                is.read(buffer);

                OutputStream stream = new FileOutputStream(file);
                stream.write(buffer);
                is.close();
                stream.close();

                System.out.println("Config created, please fill out before booting again");
                System.exit(0);
                return null;
            } else {
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                reader.lines().forEach(builder::append);
                return new HerculesConfig(Global.GSON.fromJson(builder.toString().trim(), JsonObject.class));
            }
        } catch (Exception ex) {
            System.exit(0);
            return null;
        }
    }

}
