package com.mido.dtos;

import com.mido.models.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private String chatName;
    private String text;
    private String senderUsername;
    private String receiverUsername;
    private LocalDateTime timeCreated;
}
