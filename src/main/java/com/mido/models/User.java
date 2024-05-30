package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 30, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 100, nullable = false, unique = false)
    private String password;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "role", nullable = false)
    private String role;

}
