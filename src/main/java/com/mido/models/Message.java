package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "message_text", length = 1000, nullable = false, unique = false)
    private String text;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User sender;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User receiver;
}
