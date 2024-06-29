package com.mido.controllers;

import com.mido.dtos.requests.MessageRequest;
import com.mido.models.Chat;
import com.mido.models.Message;
import com.mido.services.ChatService;
import com.mido.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;

    private final MessageService messageService;

    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/messeges/{senderUsername}/{receiverUsername}")
    public ResponseEntity<List<Message>> findMessages(@PathVariable String senderUsername, @PathVariable String receiverUsername) {
        List<Message> messages = messageService.findMessages(senderUsername, receiverUsername);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @MessageMapping("/create")
    public void processMessage(@Payload MessageRequest messageReq) {
        Message savedMsg = messageService.save(messageReq);
        messagingTemplate.convertAndSendToUser(
                savedMsg.getReceiver().getUsername(), "/queue/messages", savedMsg
        );
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Chat>> findChats(@PathVariable String username) {
        List<Chat> chats = chatService.findUserChats(username);
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }
}
