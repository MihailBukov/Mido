package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer age;
    private String country;
    private String city;
    private Integer photo;
    private String description;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
