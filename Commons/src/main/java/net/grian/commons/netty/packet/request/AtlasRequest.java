package net.grian.commons.netty.packet.request;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.grian.commons.Global;
import net.grian.commons.Protocol;
import net.grian.commons.netty.Packet;
import net.grian.commons.netty.packet.request.enums.RequestType;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.channels.Channel;

@NoArgsConstructor
public class AtlasRequest implements Packet {

    @Getter
    private RequestType requestType;
    @Getter
    private String id;
    @Getter
    private JsonObject payload;
    @Setter
    @Getter
    private Channel channel;

    public AtlasRequest(RequestType requestType, Object payload) {
        this(requestType, payload, null);
    }

    public AtlasRequest(RequestType requestType, Object payload, String user) {
        this.requestType = requestType;
        if(payload instanceof JsonObject)
            this.payload = (JsonObject) payload;
        else
            this.payload = Global.GSON.fromJson(Global.GSON.toJson(payload), JsonObject.class);
        this.id = RandomStringUtils.random(16, true, true) + "@" + (user == null ? "NO_USER" : user);
    }

    @Override
    public int packetId() {
        return Protocol.Packet.Request;
    }

}
