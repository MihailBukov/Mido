package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PetShelterPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer photo;

    @OneToOne
    @JoinColumn(name = "pet_shelter_id", nullable = false)
    private PetShelter shelter;
}
