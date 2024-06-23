package com.mido.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdvertisementDto {
    private String title;
    private LocalDateTime timeOfCreation;
    private String description;
    private String dogName;
    private Integer dogAge;
    private Double dogKg;
    private String dogBreed;
    private String dogGender;
    private String dogColor;
    private String country;
    private String city;
}
