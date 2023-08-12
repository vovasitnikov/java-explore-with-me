package com.github.explore_with_me.main.user.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email",
            nullable = false,
            length = 100)
    private String email;
    @Column(name = "name",
            nullable = false,
            length = 100)
    private String name;
}
