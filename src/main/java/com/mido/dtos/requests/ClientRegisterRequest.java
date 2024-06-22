package com.mido.dtos.requests;

public record ClientRegisterRequest(
        String firstName,
        String middleName,
        String lastName,
        int age,
        String country,
        String city,
        String description
) { }
