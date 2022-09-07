package com.iloveleiyuxin.websitmanager.mqtt;

import io.moquette.BrokerConstants;
import io.moquette.broker.Server;
import io.moquette.broker.config.MemoryConfig;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Properties;

@Service
public class MoquetteServer {

    private Server mqttServer;

    public void startServer() throws IOException {
        mqttServer = new Server();
        MemoryConfig memoryConfig = new MemoryConfig(new Properties());
        memoryConfig.setProperty(BrokerConstants.WEB_SOCKET_PORT_PROPERTY_NAME,"8883");
        mqttServer.startServer(memoryConfig);
    }


    public void stop() {
        mqttServer.stopServer();
    }

}
