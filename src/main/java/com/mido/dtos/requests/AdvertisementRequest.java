package com.mido.dtos.requests;

import java.time.LocalDateTime;

public record AdvertisementRequest(
        String title,
        LocalDateTime timeOfCreation,
        String description,
        String dogName,
        Integer dogAge,
        Double dogKg,
        String dogBreed,
        String dogGender,
        String dogColor,
        String country,
        String city
) { }
