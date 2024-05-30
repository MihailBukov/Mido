package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_rating")
public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rating")
    private Integer rating;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userRates;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userRated;
}
