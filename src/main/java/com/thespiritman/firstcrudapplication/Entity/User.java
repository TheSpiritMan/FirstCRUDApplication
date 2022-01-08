package com.thespiritman.firstcrudapplication.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 30 , nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String firstname;

    @Column(length = 30, nullable = false)
    private String lastname;
}
