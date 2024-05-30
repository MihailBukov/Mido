package com.mido.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = 100, nullable = false, unique = false)
    private String firstName;

    @Column(name = "middle_name", length = 100, nullable = false, unique = false)
    private String middleName;

    @Column(name = "last_name", length = 100, nullable = false, unique = false)
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "country", length = 100, nullable = false, unique = false)
    private String country;

    @Column(name = "city", length = 100, nullable = false, unique = false)
    private String city;

    @Column(name = "photo")
    private String photo;

    @Column(name = "description", length = 500, nullable = false, unique = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
