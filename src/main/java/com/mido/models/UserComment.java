package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_comment")
public class UserComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User writtenTo;

    @Column(name = "comment", length = 300, nullable = false, unique = false)
    private String comment;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User writtenBy;
}
