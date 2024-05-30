package com.mido.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "advertisements")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", length = 100, nullable = false, unique = false)
    private String title;

    @Column(name = "created_at")
    private LocalDateTime timeOfCreation;

    @Column(name = "description", length = 500, nullable = false, unique = false)
    private String description;

    @Column(name = "dog_name", length = 50, nullable = false, unique = false)
    private String dogName;

    @Column(name = "dog_age", nullable = false)
    private Integer dogAge;

    @Column(name = "dog_kg", nullable = false)
    private Double dogKg;

    @Column(name = "dog_breed", nullable = false)
    private String dogBreed;

    @Column(name = "dog_gender", length = 6, nullable = false, unique = false)
    private String dogGender;

    @Column(name = "dog_color", length = 20, nullable = false, unique = false)
    private String dogColor;
}
