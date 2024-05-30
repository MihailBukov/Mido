package com.mido.services;

import com.mido.models.Message;
import com.mido.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message addMessage(Message message) {
        messageRepository.save(message);
        return message;
    }
}
