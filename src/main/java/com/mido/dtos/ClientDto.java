package com.mido.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClientDto(String firstName,
                        String middleName,
                        String lastName,
                        Integer age,
                        String country,
                        String city,
                        @JsonProperty("photo") String photoFilePath,
                        String description) {}
