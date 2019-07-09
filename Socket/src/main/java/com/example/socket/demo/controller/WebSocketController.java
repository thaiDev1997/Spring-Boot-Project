package com.example.socket.demo.controller;

import com.example.socket.demo.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    // client gửi message đến Message Mapping
    @MessageMapping("/chat.addUser")
    // Send To: Server gửi message đến client đã đăng ký (subscribe) với topic/publicChatRoom
    @SendTo("/topic/publicChatRoom")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        System.out.println(chatMessage.getSender() + " :" + chatMessage.getContent() + "[" + chatMessage.getType() +"]");
        return chatMessage;
    }

    // client gửi message đến Message Mapping
    @MessageMapping("/chat.sendMessage")
    // Send To: Server gửi message đến client đã đăng ký (subscribe) với topic/publicChatRoom
    @SendTo("/topic/publicChatRoom")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println(chatMessage.getSender() + " :" + chatMessage.getContent() + "[" + chatMessage.getType() +"]");
        return chatMessage;
    }

}