package com.mido.dtos;

import lombok.Data;

@Data
public class AdvertisementCommentDto {
    private Long advertisementId;
    private String comment;
    private String writtenByUsername;
}
