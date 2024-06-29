package com.mido.mappers;

import com.mido.dtos.UserRatingDto;
import com.mido.dtos.requests.UserRatingRequest;
import com.mido.models.Client;
import com.mido.models.PetShelter;
import com.mido.models.User;
import com.mido.models.UserRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRatingMapper {
    @Mapping(source = "userRates.username", target = "userRates")
    @Mapping(source = "userRated.username", target = "userRated")
    @Mapping(target = "userRatesName", expression = "java(rating.getUserRates().getNameOfUser())")
    @Mapping(target = "userRatedName", expression = "java(rating.getUserRated().getNameOfUser())")
    UserRatingDto ratingToRatingDto(UserRating rating);

}
