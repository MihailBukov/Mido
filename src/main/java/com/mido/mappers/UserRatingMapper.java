package com.mido.mappers;

import com.mido.dtos.UserRatingDto;
import com.mido.dtos.requests.UserRatingRequest;
import com.mido.models.UserRating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRatingMapper {
    UserRatingDto ratingToRatingDto(UserRating rating);
}
