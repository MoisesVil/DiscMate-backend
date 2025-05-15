package com.discgolf.disc_golf_backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Course")
@Data
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int numHoles;

    private String courseName;

    private int par;

    private int distance;

    private double lat;

    private double lng;

    @OneToMany(mappedBy = "course")
    @JsonManagedReference("course-round")
    private List<Round> rounds = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    @JsonManagedReference
    private List<Hole> holes = new ArrayList<>();

    public Course(int numHoles, String name, int par, int distance, double lat, double lng, List<Round> rounds, List<Hole> holes) {
        this.numHoles = numHoles;
        this.courseName = name;
        this.par = par;
        this.distance = distance;
        this.lat = lat;
        this.lng = lng;
        this.rounds = rounds;
        this.holes = holes;
    }

    public void addRound(Round round) {
        this.rounds.add(round);
    }
}