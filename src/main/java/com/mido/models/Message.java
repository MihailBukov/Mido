package com.mido.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_name", nullable = false)
    private String chatName;

    @Column(name = "message_text", length = 1000, nullable = false)
    private String text;

    @OneToOne
    @JoinColumn(name = "sender", nullable = false)
    private User sender;

    @OneToOne
    @JoinColumn(name = "receiver", nullable = false)
    private User receiver;

    @Column(name = "time", nullable = false)
    private LocalDateTime timeCreated;
}
