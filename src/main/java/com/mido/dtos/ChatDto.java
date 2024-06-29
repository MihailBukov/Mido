package com.mido.dtos;

import com.mido.models.User;
import lombok.Data;

@Data
public class ChatDto {
    private String chatName;

    private String sender;

    private String receiver;
}
