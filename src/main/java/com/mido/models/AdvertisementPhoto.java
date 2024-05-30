package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "advertisements_photos")
public class AdvertisementPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "advertisement_photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;
}
