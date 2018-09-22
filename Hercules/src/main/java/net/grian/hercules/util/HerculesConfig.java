package net.grian.hercules.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HerculesConfig {

    public static final String InstanceName = "name";
    public static final String AtlasHost = "atlas_host";
    public static final String AtlasPort = "atlas_port";
    public static final String MongoHost = "mongo_host";
    public static final String MongoPort = "mongo_port";
    public static final String MongoDatabase = "mongo_database";
    public static final String MongoPassword = "mongo_password";
    public static final String MongoUsername = "mongo_username";
    public static final String VersionsSupported = "versions";

    private final JsonObject object;

    public String getStringProperty(String key) {
        return object.get(key).getAsString();
    }

    public int getIntProperty(String key) {
        return object.get(key).getAsInt();
    }

    public boolean getBooleanProperty(String key) {
        return object.get(key).getAsBoolean();
    }

    public JsonArray getArrayProperty(String key) {
        return object.get(key).getAsJsonArray();
    }

}
