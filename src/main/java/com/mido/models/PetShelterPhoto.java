package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pet_shelter_photo")
public class PetShelterPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "photo")
    private String photo;

    @OneToOne
    @JoinColumn(name = "pet_shelter_id", nullable = false)
    private PetShelter shelter;
}
