package com.mido.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pet_shelters")
public class PetShelter extends User {

    @Column(name = "shelter_name", length = 100, unique = true)
    private String name;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "pet_shelter_description", length = 500)
    private String description;

    @Column(name = "pet_shelter_photo")
    private String photoFilePath;

    @Column(name = "verification")
    private Boolean isVerified;

    @Override
    public String getNameOfUser() {
        return name;
    }
}
