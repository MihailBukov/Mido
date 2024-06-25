package com.mido.mappers;

import com.mido.dtos.AdvertisementCommentDto;
import com.mido.models.AdvertisementComment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdvertisementCommentMapper {
    AdvertisementCommentDto adCommentToDto(AdvertisementComment ad);
}
