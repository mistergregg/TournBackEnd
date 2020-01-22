package com.collabera.tourn2.controller;

import com.collabera.tourn2.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class WebSocketController {

    @MessageMapping("/chat")
    @SendTo("/topic/reply")
    public ChatMessage send(ChatMessage message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new ChatMessage();
    }
}
