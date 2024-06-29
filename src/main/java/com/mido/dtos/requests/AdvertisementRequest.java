package com.mido.dtos.requests;

import java.time.LocalDateTime;

public record AdvertisementRequest(
        String title,
        LocalDateTime timeOfCreation,
        String description,
        String dogName,
        String dogAge,
        String dogKg,
        String dogBreed,
        String dogGender,
        String dogColor,
        String country,
        String city,
        String createdBy
) { }
