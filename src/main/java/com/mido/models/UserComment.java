package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User writtenTo;

    private String comment;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User writtenBy;

    public UserComment() {
    }

    public UserComment(User writtenTo, String comment, User writtenBy) {
        this.writtenTo = writtenTo;
        this.comment = comment;
        this.writtenBy = writtenBy;
    }
}
