package com.discgolf.disc_golf_backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Round")
@Data
@NoArgsConstructor
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "round_to_user_id", nullable = true)
    @JsonBackReference("user-round")
    private User user;

    @ManyToOne
    @JoinColumn(name = "round_to_course_id", nullable = false)
    @JsonBackReference("course-round")
    private Course course;

    private LocalDateTime datePlayed;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL)
    @JsonManagedReference("round-scores")
    private List<RoundScore> scores = new ArrayList<>();

    public Round(User user, Course course, LocalDateTime date) {
        this.user = user;
        this.course = course;
        this.datePlayed = date;
    }

    public void AddScore(RoundScore score) {
        scores.add(score);
    }
}
