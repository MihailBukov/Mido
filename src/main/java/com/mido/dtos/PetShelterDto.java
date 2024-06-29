package com.mido.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PetShelterDto(String name,
                            String country,
                            String city,
                            Integer capacity,
                            String address,
                            String description,
                            @JsonProperty("photo") String photoFilePath,
                            Boolean isVerified) {
}
