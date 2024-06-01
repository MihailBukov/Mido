package com.mido.services;

import com.mido.models.Message;
import com.mido.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message addMessage(Message message) {
        messageRepository.save(message);
        return message;
    }
}
