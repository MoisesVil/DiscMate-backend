package com.discgolf.disc_golf_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Hole")
@Data
@NoArgsConstructor
public class Hole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = true)
    @JsonBackReference
    private Course course;

    @Column(name = "hole_num", nullable = false)
    private int holeNum;

    private int par;

    private int distance;

    private float teepadLat;

    private float teepadLng;  

    private float basketLat;

    private float basketLng;

    public Hole(Course course, int holeNum, int par, int dist, float teepadLat,
     float teepadLng, float basketLat, float basketLng) {
        this.course = course;
        this.holeNum = holeNum;
        this.par = par;
        this.distance = dist;
        this.teepadLat = teepadLat;
        this.teepadLng = teepadLng;
        this.basketLat = basketLat;
        this.basketLng = basketLng;
    }
}
