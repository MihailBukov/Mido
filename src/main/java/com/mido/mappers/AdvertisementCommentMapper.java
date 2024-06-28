package com.mido.mappers;

import com.mido.dtos.AdvertisementCommentDto;
import com.mido.models.AdvertisementComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdvertisementCommentMapper {
    @Mapping(source = "writtenBy.username", target = "writtenByUsername")
    @Mapping(source = "advertisement.id", target = "advertisementId")
    AdvertisementCommentDto adCommentToDto(AdvertisementComment ad);
}
