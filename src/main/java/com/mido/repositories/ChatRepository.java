package com.mido.repositories;

import com.mido.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    Optional<Chat> findBySenderUsernameAndReceiverUsername(String senderUsername, String receiverUsername);

    Optional<Chat> findByChatName(String chatName);
}
