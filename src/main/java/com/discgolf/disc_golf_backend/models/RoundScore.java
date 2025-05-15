package com.discgolf.disc_golf_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RoundScore")
@Data
@NoArgsConstructor
public class RoundScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "score_to_round_id")
    @JsonBackReference("round-scores")
    private Round round;

    private int holeNum;
    
    private int strokes;

    public RoundScore(Round round, int holeNum, int strokes) {
        this.round = round;
        this.holeNum = holeNum;
        this.strokes = strokes;
    }
}
