package com.mido.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "advertisements_comments")
public class AdvertisementComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "advertisement_id", nullable = false)
    private Long advertisementId;

    @Column(name = "comment", length = 300)
    private String comment;

    @Column(name = "written_by", nullable = false)
    private String writtenBy;
}
