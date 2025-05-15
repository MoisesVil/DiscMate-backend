package com.discgolf.disc_golf_backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-round")
    private List<Round> rounds = new ArrayList<>();

    public User(String username, String pass, List<Round> rounds) {
        this.username = username;
        this.password = pass;
        this.rounds = rounds;
    }

    public void AddRound(Round r) {
        rounds.add(r);
    }
}