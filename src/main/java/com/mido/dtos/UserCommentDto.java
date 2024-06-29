package com.mido.dtos;

import lombok.Data;

@Data
public class UserCommentDto {
    private String writtenTo;
    private String writtenToName;
    private String comment;
    private String writtenBy;
    private String writtenByName;
}
