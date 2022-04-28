package com.iloveleiyuxin.websitmanager.controller.websocket;

import org.springframework.web.bind.annotation.RestController;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@RestController
@ServerEndpoint("/socketTest")
public class TestSocketController {
    @OnOpen
    public void onOpen(Session session){
        System.out.println("启动WebSocket");
    }

    @OnClose
    public void onClose(){

    }

}
