package com.mido.dtos.requests;

import com.mido.models.User;

public record UserRatingRequest(
        Integer rating,
        String userRates,
        String userRated
) { }
