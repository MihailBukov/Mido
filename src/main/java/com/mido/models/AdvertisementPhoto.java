package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AdvertisementPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;
}
