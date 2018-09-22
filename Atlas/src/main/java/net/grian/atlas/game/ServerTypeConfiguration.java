package net.grian.atlas.game;

public class ServerTypeConfiguration {

    private String base_name;
    private GameConfig game_config;
    private LobbyConfig lobby_config;

    private static final class GameConfig {



    }

    private static final class LobbyConfig {

        private int max_lobbies;
        private String name_pattern;

    }

    public boolean hasGameConfig() {
        return game_config != null;
    }

    public boolean hasLobbyConfig() {
        return lobby_config != null;
    }

    public GameConfig getGameConfig() {
        return game_config;
    }

    public LobbyConfig getLobbyConfig() {
        return lobby_config;
    }

    public String getBaseName() {
        return base_name;
    }

}
