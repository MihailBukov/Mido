package com.mido.services;

import com.mido.dtos.requests.MessageRequest;
import com.mido.models.Message;
import com.mido.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatService chatService;
    private final UserService userService;

    public Message save(MessageRequest req) {
        String chatName = chatService.getChatRoomName(req.senderUsername(), req.receiverUsername(), true).orElse(null);
        Message newMessage = new Message();
        newMessage.setChatName(chatName);
        newMessage.setText(req.text());
        checkUser(req.senderUsername(), newMessage);
        checkUser(req.receiverUsername(), newMessage);
        newMessage.setTimeCreated(LocalDateTime.now());
        messageRepository.saveAndFlush(newMessage);
        return newMessage;
    }

    public void checkUser(String username, Message newMessage) {
        if(username != null && userService.loadUserByUsername(username) != null) {
            newMessage.setSender(userService.loadUserByUsername(username));
        } else {
            throw new IllegalArgumentException("Such user doesn't exist");
        }
    }

    public List<Message> findMessages(String senderUsername, String receiverUsername) {
        Optional<String> chatName = chatService.getChatRoomName(senderUsername, receiverUsername, false);
        return chatName.map(messageRepository::findByChatName).orElse(new ArrayList<>());
    }
}