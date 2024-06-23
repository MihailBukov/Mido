package com.mido.services;

import com.mido.models.Chat;
import com.mido.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public Optional<String> getChatRoomName(String senderUsername, String receiverUsername, boolean createNewChatIfNotExists) {
        return chatRepository.findBySenderAndReceiver(senderUsername, receiverUsername)
                .map(Chat::getChatName)
                .or(() -> {
                    if(createNewChatIfNotExists) {
                        String chatName = createChat(senderUsername, receiverUsername);
                        return Optional.of(chatName);
                    }

                    return Optional.empty();
                });
    }

    public String createChat(String sender, String receiver) {
        String chatName =  String.format("%s_%s", sender, receiver);
        Chat senderReceiver = new Chat();

        senderReceiver.setChatName(chatName);
        senderReceiver.setSenderUsername(sender);
        senderReceiver.setReceiverUsername(receiver);

        Chat receiverSender = new Chat();

        senderReceiver.setChatName(chatName);
        senderReceiver.setSenderUsername(receiver);
        senderReceiver.setReceiverUsername(sender);

        chatRepository.saveAndFlush(senderReceiver);
        chatRepository.saveAndFlush(receiverSender);

        return chatName;
    }
}
