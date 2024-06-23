package com.mido.models;

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
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_name", length = 100, nullable = false)
    private String chatName;

    @Column(name = "sender_username", nullable = false)
    private String senderUsername;

    @Column(name = "receiver_username", nullable = false)
    private String receiverUsername;
}
