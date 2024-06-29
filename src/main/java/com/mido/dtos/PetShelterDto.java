package com.mido.dtos;

public record PetShelterDto(String name,
                            String country,
                            String city,
                            Integer capacity,
                            String address,
                            String description,
                            String photoFilePath,
                            Boolean isVerified) {
}
