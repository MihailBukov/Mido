package com.mido.dtos;

public record ClientDto(String firstName,
                        String middleName,
                        String lastName,
                        Integer age,
                        String country,
                        String city,
                        String photoFilePath,
                        String description) {}
