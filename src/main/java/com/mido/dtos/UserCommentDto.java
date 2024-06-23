package com.mido.dtos;

import com.mido.models.User;
import lombok.Data;

@Data
public class UserCommentDto {
    private User writtenTo;
    private String comment;
    private User writtenBy;
}
