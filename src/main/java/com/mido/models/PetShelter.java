package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pet_shelters")
public class PetShelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "shelter_name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "country", length = 100, nullable = false, unique = false)
    private String country;

    @Column(name = "city", length = 100, nullable = false, unique = false)
    private String city;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "address", length = 200, nullable = false, unique = false)
    private String address;

    @Column(name = "pet_shelter_description", length = 500, nullable = false, unique = false)
    private String description;

    @Column(name = "pet_shelter_photo")
    private String photo;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "verification")
    private Boolean isVerified;
}
