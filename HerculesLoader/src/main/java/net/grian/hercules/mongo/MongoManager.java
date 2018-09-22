package net.grian.hercules.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.async.client.MongoClients;
import com.mongodb.connection.ServerSettings;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.MessageFormat;

public class MongoManager {

    @AllArgsConstructor
    public static final class Credentials {
        private String username;
        private String password;
    }

    @Getter
    private MongoClient client;

    public MongoManager(String host, int port, Credentials credentials)
    {
        MongoClientSettings settings = MongoClientSettings.builder()
                .serverSettings(ServerSettings.builder().applyConnectionString(new ConnectionString(
                        MessageFormat.format("mongodb://{0}:{1}@{2}:{3}",
                                credentials.username, credentials.password, host, port)
                )).build())
                .build();

        client = MongoClients.create(settings);
    }

}
