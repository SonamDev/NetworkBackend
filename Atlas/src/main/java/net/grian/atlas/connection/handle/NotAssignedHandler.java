package net.grian.atlas.connection.handle;

import net.grian.atlas.connection.ServerConnection;
import net.grian.commons.Global;
import net.grian.commons.Protocol;
import net.grian.commons.netty.AbstractPacketHandler;
import net.grian.commons.netty.Connection;
import net.grian.commons.netty.PacketHandle;
import net.grian.commons.netty.packet.main.*;

@SuppressWarnings("FieldCanBeLocal")
public class NotAssignedHandler extends AbstractPacketHandler {

    private boolean authenticated;
    private boolean registered;

    private static final int SUCCESS = 0;
    private static final int SERVER_GAME_TYPE_NULL = 1;
    private static final int INVALID_SERVER_TYPE = 2;
    private static final int INVALID_GAME_TYPE = 3;

    public NotAssignedHandler(Connection connection) {
        this.connection = connection;
        addHandles
        (
                AuthRequestHandle,
                RegisterRequestHandle,
                DebugHandler
        );
    }

    private final PacketHandle<AuthRequest> AuthRequestHandle = new PacketHandle<AuthRequest>(Protocol.Packet.AuthRequest) {
        @Override
        public void handle(AuthRequest request) {
            if(request.getKey().equals(Global.AUTH_KEY))
                getConnection().write(new AuthResponse(true));
            else
                getConnection().write(new AuthResponse(false));
        }
    };

    private final PacketHandle<RegisterRequest> RegisterRequestHandle = new PacketHandle<RegisterRequest>(Protocol.Packet.RegisterRequest) {
        @Override
        public void handle(RegisterRequest request) {

            switch (validateRegister(request)) {
                case SUCCESS:
                    break;
                case SERVER_GAME_TYPE_NULL:
                    break;
                case INVALID_SERVER_TYPE:
                    break;
                case INVALID_GAME_TYPE:
                    break;
            }

            System.out.println("Registered " + request.getInfo().toString());
            ((ServerConnection) connection).setInfo(request.getInfo());
            getConnection().write(new RegisterResponse(true, null));
        }
    };

    private final PacketHandle<Debug> DebugHandler = new PacketHandle<Debug>(Protocol.Packet.Debug) {
        @Override
        public void handle(Debug debug) {
            System.out.println(debug.getTest());
        }
    };

    private int validateRegister(RegisterRequest request) {
        if(request.getInfo().getConnectionType() == Global.ConnectionType.Spigot) {
            if(request.getInfo().getServerType() == null && request.getInfo().getGameType() == null)
                return SERVER_GAME_TYPE_NULL;

        }
        return SUCCESS;
    }

}
