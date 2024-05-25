package com.mido.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private LocalDateTime timeOfCreation;
    private String description;
    private String dogName;
    private Integer dogAge;
    private Double dogKg;
    private String dogBreed;
    private String dogGender;
    private String dogColor;

    public Advertisement() {
    }

    public Advertisement(String title, LocalDateTime timeOfCreation, String description, String dogName, Integer dogAge, Double dogKg, String dogBreed, String dogGender, String dogColor) {
        this.title = title;
        this.timeOfCreation = timeOfCreation;
        this.description = description;
        this.dogName = dogName;
        this.dogAge = dogAge;
        this.dogKg = dogKg;
        this.dogBreed = dogBreed;
        this.dogGender = dogGender;
        this.dogColor = dogColor;
    }
}
