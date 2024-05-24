package com.mido.mappers;

import com.mido.dtos.UserRatingDto;
import com.mido.models.UserRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRatingMapper {

    @Mapping(source = "", target = "")
    UserRating mapFromDto(UserRatingDto ratingDto);

    @Mapping(source = "", target = "")
    UserRatingDto mapToDto(UserRating rating);
}
