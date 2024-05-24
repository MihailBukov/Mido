package com.mido.dtos;

import com.mido.models.User;
import lombok.Data;

@Data
public class MessageDto {
    private String text;
    private User sender;
    private User receiver;

}
