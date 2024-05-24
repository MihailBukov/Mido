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

    public AdvertisementComment() {
    }

    public AdvertisementComment(Advertisement advertisement, String comment, User writtenBy) {
        this.advertisement = advertisement;
        this.comment = comment;
        this.writtenBy = writtenBy;
    }
}
