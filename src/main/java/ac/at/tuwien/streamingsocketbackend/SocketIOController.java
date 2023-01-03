package ac.at.tuwien.streamingsocketbackend;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class SocketIOController {

    @Autowired
    private SocketIOServer server;

    SocketIOController(SocketIOServer server) {
        this.server = server;
        this.server.addConnectListener(onUserConnectWithSocket);
        this.server.addDisconnectListener(onUserDisconnectWithSocket);
        this.server.addEventListener("getAllDashboardData", String.class, onGetAllDashboardData);
    }

    Logger log = Logger.getLogger(SocketIOController.class.getName());

    public ConnectListener onUserConnectWithSocket = new ConnectListener() {
        @Override
        public void onConnect(SocketIOClient socketIOClient) {
            log.info("Client connected: " + socketIOClient.getSessionId());
            socketIOClient.sendEvent("getAllDashboardData", generateRandomValues().toString());
        }
    };

    public DisconnectListener onUserDisconnectWithSocket = new DisconnectListener() {
        @Override
        public void onDisconnect(SocketIOClient socketIOClient) {
            log.info("Client disconnected: " + socketIOClient.getSessionId());
        }
    };

    public DataListener<String> onGetAllDashboardData = new DataListener<String>() {
        @Override
        public void onData(SocketIOClient socketIOClient, String message, AckRequest ackRequest) throws Exception {
            log.warning("Message received: " + message);
            server.getBroadcastOperations().sendEvent(generateRandomValues().toString());
        }
    };

    private List<DashboardEntity> generateRandomValues(){
        List<DashboardEntity> dashboardEntities = new ArrayList<>();
        int id = 1;
        double lat = 39.908829698;
        double lng = 116.387665116;
        double distance = 1;
        double avgSpeed = 10;
        for (double i = 0; i < 1; i += 0.1) {
            dashboardEntities.add(new DashboardEntity(String.valueOf(id), "Taxi" + id, lat+i, lng-i, distance+id, avgSpeed+id));
            id++;
        }
        return dashboardEntities;
    }
}
