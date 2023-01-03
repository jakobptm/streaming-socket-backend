package ac.at.tuwien.streamingsocketbackend;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.logging.Logger;

@CrossOrigin
@Component
public class SocketIOConfig {

    private final String host;

    private final int port;

    private SocketIOServer server;

    public SocketIOConfig() {
        this.host = "localhost";
        this.port = 8080;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    Logger log = Logger.getLogger(SocketIOConfig.class.getName());

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname(host);
        config.setPort(port);

        log.info("Starting socket.io server at " + host + ":" + port);
        server = new SocketIOServer(config);
        server.start();
        log.info("Socket.io server started");

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                log.info("Client connected: " + socketIOClient.getSessionId());
            }
        });

        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient socketIOClient) {
                socketIOClient.getNamespace().getAllClients().forEach(client -> {
                    log.info("Client disconnected: " + client.getSessionId());
                });
            }
        });

        return server;
    }

    @PreDestroy
    public void stop() {
        log.info("Stopping socket.io server");
        server.stop();
        log.info("Socket.io server stopped");
    }
}
