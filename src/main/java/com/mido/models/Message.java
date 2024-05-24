package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User sender;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User receiver;

    public Message() {
    }

    public Message(String text, User sender, User receiver) {
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
    }
}
