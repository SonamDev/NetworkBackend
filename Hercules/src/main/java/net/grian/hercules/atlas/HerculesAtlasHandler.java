package net.grian.hercules.atlas;

import net.grian.atlas.Atlas;
import net.grian.commons.Protocol;
import net.grian.commons.netty.AbstractPacketHandler;
import net.grian.commons.netty.PacketHandle;
import net.grian.commons.netty.packet.main.AuthResponse;
import net.grian.commons.netty.packet.main.RegisterRequest;
import net.grian.commons.netty.packet.main.RegisterResponse;

public class HerculesAtlasHandler extends AbstractPacketHandler {

    public HerculesAtlasHandler() {
        addHandles(AuthResponseHandle,
                RegisterResponseHandle

        );
    }

    private PacketHandle<AuthResponse> AuthResponseHandle = new PacketHandle<AuthResponse>(Protocol.Packet.AuthResponse) {
        @Override
        public void handle(AuthResponse response) {
            if(response.isSuccess()) {
//                System.out.println(ChatColor.BLUE + "ATLAS " + ChatColor.AQUA + "Authenticated");
                System.out.println("Authenticated");
                connection.write(new RegisterRequest(Atlas.instance().info()));
            }

        }
    };

    private PacketHandle<RegisterResponse> RegisterResponseHandle = new PacketHandle<RegisterResponse>(Protocol.Packet.RegisterResponse) {
        @Override
        public void handle(RegisterResponse registerResponse) {
            if(registerResponse.isSuccessful()) {
                //                System.out.println(ChatColor.BLUE + "ATLAS " + ChatColor.AQUA + "Registered as " + Hercules.instance().config().getStringProperty(HerculesConfig.InstanceName));
                System.out.println("Registered");
            } else {
                System.out.println("Registering Failed: " + registerResponse.getFailReason());
            }

        }
    };



}
