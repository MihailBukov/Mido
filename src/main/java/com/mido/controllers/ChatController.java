package com.mido.controllers;

import com.mido.dtos.requests.MessageRequest;
import com.mido.models.Message;
import com.mido.models.Notification;
import com.mido.services.ChatService;
import com.mido.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {
    private ChatService chatService;

    private MessageService messageService;

    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/messeges/{senderUsername}/{receiverUsername}")
    public ResponseEntity<List<Message>> findMessages(@PathVariable String senderUsername, @PathVariable String receiverUsername) {
        return new ResponseEntity<>(messageService.findMessages(senderUsername, receiverUsername), HttpStatus.OK);
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload MessageRequest messageReq) {
        Message savedMsg = messageService.save(messageReq);
        Notification notification = new Notification();
        notification.setId(savedMsg.getId());
        notification.setSenderUsername(savedMsg.getSenderUsername());
        notification.setReceiverUsername(savedMsg.getReceiverUsername());
        notification.setText(savedMsg.getText());
        messagingTemplate.convertAndSendToUser(
                savedMsg.getReceiverUsername(), "/queue/messages", notification
        );
    }
}
