package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PetShelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String country;
    private String city;
    private String capacity;
    private String address;
    private String description;
    private Integer photo;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Boolean isVerified;

    public PetShelter() {
    }

    public PetShelter(String name, String country, String city, String capacity, String address, String description, Integer photo, User user, Boolean isVerified) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.capacity = capacity;
        this.address = address;
        this.description = description;
        this.photo = photo;
        this.user = user;
        this.isVerified = isVerified;
    }
}
