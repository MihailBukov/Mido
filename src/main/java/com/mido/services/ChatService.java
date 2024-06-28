package com.mido.services;

import com.mido.models.Chat;
import com.mido.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    private final UserService userService;

    public Optional<String> getChatRoomName(String senderUsername, String receiverUsername, boolean createNewChatIfNotExists) {
        return chatRepository.findBySenderUsernameAndReceiverUsername(senderUsername, receiverUsername)
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
        Chat newChat = new Chat();

        newChat.setChatName(chatName);
        newChat.setSender(userService.loadUserByUsername(sender));
        newChat.setReceiverUsername(userService.loadUserByUsername(receiver));

        chatRepository.saveAndFlush(newChat);

        return chatName;
    }

    public List<Chat> findUserChats(String username) {
        return chatRepository.findAll().stream()
                .filter(chat -> chat.getChatName().contains(username))
                .collect(Collectors.toList());
    }

    public Optional<Chat> findByName(String chatName) {
        return chatRepository.findByChatName(chatName);
    }
}
