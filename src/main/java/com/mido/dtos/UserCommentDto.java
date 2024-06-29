package com.mido.dtos;

import com.mido.models.User;
import lombok.Data;

@Data
public class UserCommentDto {
    private String writtenTo;
    private String comment;
    private String writtenBy;
}
