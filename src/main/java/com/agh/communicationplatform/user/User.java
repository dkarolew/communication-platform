package com.agh.communicationplatform.user;

import lombok.Getter;
import javax.persistence.*;

@Getter
@Entity
@Table(name = "USER")
public class User {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    public User() {}

    public User(String firstName, String lastName, String role, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.password = password;
    }
}
