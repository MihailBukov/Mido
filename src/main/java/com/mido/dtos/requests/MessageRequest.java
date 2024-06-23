package com.mido.dtos.requests;

import java.time.LocalDateTime;

public record MessageRequest(
        String chatName,
        String text,
        String senderUsername,
        String receiverUsername
) {
}
