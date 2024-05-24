package com.mido.dtos;

import com.mido.models.User;
import lombok.Data;

@Data
public class UserRatingDto {

    private Integer rating;
    private User userRates;
    private User userRated;
}
