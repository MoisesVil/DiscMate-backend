package com.discgolf.disc_golf_backend.controllers;

import com.discgolf.disc_golf_backend.models.Round;
import com.discgolf.disc_golf_backend.services.RoundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rounds")
@CrossOrigin(origins = "http://172.20.10.3:3000")
public class RoundController {

    private final RoundService roundService;

    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @GetMapping
    public List<Round> getAllRounds() {
        return roundService.getAllRounds();
    }

    @GetMapping("/username/{username}")
    public List<Round> getRoundsByUsername(@PathVariable String username) {
        return roundService.getRoundsByUsername(username);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Round> getRoundById(@PathVariable int id) {
        Optional<Round> round = roundService.getRoundById(id);
        return round.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Round createRound(@RequestBody Round round) {
        return roundService.createRound(round);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Round> updateRound(@PathVariable int id, @RequestBody Round updatedRound) {
        Round round = roundService.updateRound(id, updatedRound);
        return round != null ? ResponseEntity.ok(round) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRound(@PathVariable int id) {
        boolean deleted = roundService.deleteRound(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}