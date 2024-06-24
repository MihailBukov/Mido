package com.mido.repositories;

import com.mido.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    Optional<Chat> findBySenderUsernameAndReceiverUsername(String senderUsername, String receiverUsername);
}
