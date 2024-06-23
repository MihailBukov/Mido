package com.mido.services;

import com.mido.dtos.requests.MessageRequest;
import com.mido.models.Message;
import com.mido.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final ChatService chatService;

    public Message save(MessageRequest req) {
        String chatName = chatService.getChatRoomName(req.senderUsername(), req.receiverUsername(), true).orElse(null);
        Message newMessage = new Message();
        newMessage.setChatName(chatName);
        messageRepository.saveAndFlush(newMessage);
        return newMessage;
    }

    public List<Message> findMessages(String senderUsername, String receiverUsername) {
        Optional<String> chatName = chatService.getChatRoomName(senderUsername, receiverUsername, false);
        return chatName.map(messageRepository::findByChatName).orElse(new ArrayList<>());
    }
}
