package com.mido.dtos.requests;

public record PetShelterRegisterRequest(
        String name,
        String country,
        String city,
        Integer capacity,
        String address,
        String description
) { }
