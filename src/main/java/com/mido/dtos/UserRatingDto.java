package com.mido.dtos;

import com.mido.models.User;
import lombok.Data;

@Data
public class UserRatingDto {

    private Integer rating;
    private String userRates;
    private String userRatesName;
    private String userRated;
    private String userRatedName;
}
