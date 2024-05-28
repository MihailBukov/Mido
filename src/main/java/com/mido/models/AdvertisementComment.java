package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AdvertisementComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;

    private String comment;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User writtenBy;
}
