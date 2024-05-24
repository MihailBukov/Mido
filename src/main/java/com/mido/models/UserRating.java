package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer rating;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userRates;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userRated;

    public UserRating() {
    }

    public UserRating(Integer rating, User userRates, User userRated) {
        this.rating = rating;
        this.userRates = userRates;
        this.userRated = userRated;
    }
}
