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
@Table(name = "user_comment")
public class UserComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "written_to", nullable = false)
    private User writtenTo;

    @Column(name = "comment", length = 300, nullable = false)
    private String comment;

    @OneToOne
    @JoinColumn(name = "written_by", nullable = false)
    private User writtenBy;
}
