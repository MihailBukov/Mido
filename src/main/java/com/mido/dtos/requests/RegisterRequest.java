package com.mido.dtos.requests;

import com.mido.models.Role;

public record RegisterRequest(
        String username,
        String password,
        String email,
        Role role,
        String firstName,
        String middleName,
        String lastName,
        int age,
        String country,
        String city,
        String description,
        String address,
        String name,
        int capacity
) { }
