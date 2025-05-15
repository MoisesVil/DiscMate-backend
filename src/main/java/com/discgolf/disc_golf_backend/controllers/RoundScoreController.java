package com.discgolf.disc_golf_backend.controllers;

import com.discgolf.disc_golf_backend.models.RoundScore;
import com.discgolf.disc_golf_backend.services.RoundScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scores")
@CrossOrigin(origins = "http://172.20.10.3:3000")
public class RoundScoreController {

    private final RoundScoreService roundScoreService;

    public RoundScoreController(RoundScoreService roundScoreService) {
        this.roundScoreService = roundScoreService;
    }

    @GetMapping
    public List<RoundScore> getAllScores() {
        return roundScoreService.getAllRoundScores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoundScore> getScoreById(@PathVariable int id) {
        Optional<RoundScore> score = roundScoreService.getRoundScoreById(id);
        return score.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public RoundScore createScore(@RequestBody RoundScore score) {
        return roundScoreService.createRoundScore(score);
    }

    @PostMapping("/bulk")
    public List<RoundScore> createMultipleScores(@RequestBody List<RoundScore> scores) {
        return roundScoreService.createMultipleRoundScores(scores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoundScore> updateScore(@PathVariable int id, @RequestBody RoundScore updatedScore) {
        RoundScore score = roundScoreService.updateRoundScore(id, updatedScore);
        return score != null ? ResponseEntity.ok(score) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable int id) {
        boolean deleted = roundScoreService.deleteRoundScore(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}