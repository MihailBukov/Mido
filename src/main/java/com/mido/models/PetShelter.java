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

    @Column(name = "shelter_name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "country", length = 100, nullable = false)
    private String country;

    @Column(name = "city", length = 100, nullable = false)
    private String city;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "pet_shelter_description", length = 500, nullable = false)
    private String description;

    @Column(name = "pet_shelter_photo")
    private String photo;

    @Column(name = "verification")
    private Boolean isVerified;
}
