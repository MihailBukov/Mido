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

    public Client() {
    }

    public Client(String firstName, String middleName, String lastName, Integer age, String country, String city, Integer photo, String description, User user) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.city = city;
        this.photo = photo;
        this.description = description;
        this.user = user;
    }
}
