package ac.at.tuwien.streamingsocketbackend;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.logging.Logger;

@CrossOrigin
@Component
public class SocketIOConfig {

    private final String SOCKET_HOST;

    private final int SOCKET_PORT;

    private SocketIOServer server;

    public SocketIOConfig() {
        this.SOCKET_HOST = "localhost";
        this.SOCKET_PORT = 8085;
    }

    public int getPort() {
        return SOCKET_PORT;
    }

    public String getHost() {
        return SOCKET_HOST;
    }

    Logger log = Logger.getLogger(SocketIOConfig.class.getName());

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname(SOCKET_HOST);
        config.setPort(SOCKET_PORT);

        log.info("Starting socket.io server at " + SOCKET_HOST + ":" + SOCKET_PORT);
        server = new SocketIOServer(config);
        server.start();
        log.info("Socket.io server started");

        return server;
    }

    @PreDestroy
    public void stop() {
        log.info("Stopping socket.io server");
        server.stop();
        log.info("Socket.io server stopped");
    }
}
