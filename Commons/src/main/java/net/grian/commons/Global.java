package net.grian.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.grian.commons.util.Callback;

public class Global {

    public enum ServerType {
        LOBBY,
        GAME,
        DEV
    }

    public static final String AUTH_KEY = "j^6&@1@h!ZjM91#jL5TdZ8Y1y3twUVTDQ^hmln$5sT93fY!cM1";
    public static final String AUTOMATIC_NAME = "%_automatic_%";

    public static final Gson GSON = new Gson();
    public static final ObjectMapper MAPPER = new ObjectMapper();
    public static final Gson PRETTY_PRINTING = new GsonBuilder().setPrettyPrinting().create();


    public static final class Pipeline {
        public static final String FrameDecoderKey = "frame_decoder";
        public static final String FramePrependerKey = "frame_prepender";
        public static final String AtlasDecoderKey = "atlas_decoder";
        public static final String BaseHandlerKey = "base_handler";
        public static final String AtlasEncoderKey = "atlas_encoder";
    }

    public static final class ConnectionType {
        public static final byte Bungee = 0;
        public static final byte Spigot = 1;
        public static final byte Coordinator = 2;
    }

    public static final class Defaults {

        public static Callback<Exception> MainExceptionHandler = exception ->
        {
            exception.printStackTrace();
        };

    }

}
