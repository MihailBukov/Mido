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
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_text", length = 1000, nullable = false)
    private String text;

    @OneToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @OneToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;
}
