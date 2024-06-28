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
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "sender", nullable = false)
    private User sender;

    @OneToOne
    @JoinColumn(name = "receiver", nullable = false)
    private User receiverUsername;

    @Column(name = "text", nullable = false)
    private String text;
}
