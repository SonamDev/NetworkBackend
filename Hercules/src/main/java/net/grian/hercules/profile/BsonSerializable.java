package net.grian.hercules.profile;

import org.bson.Document;

public interface BsonSerializable {

    Document toBson();

}
