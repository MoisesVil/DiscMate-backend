package com.discgolf.disc_golf_backend.services;

import com.discgolf.disc_golf_backend.models.Round;
import com.discgolf.disc_golf_backend.models.RoundScore;
import com.discgolf.disc_golf_backend.repository.RoundScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoundScoreService {

    private final RoundScoreRepository roundScoreRepository;

    public RoundScoreService(RoundScoreRepository roundScoreRepository) {
        this.roundScoreRepository = roundScoreRepository;
    }

    public List<RoundScore> getAllRoundScores() {
        return roundScoreRepository.findAll();
    }

    public Optional<RoundScore> getRoundScoreById(int id) {
        return roundScoreRepository.findById(id);
    }

    public List<RoundScore> getScoresByRound(Round round) {
        return roundScoreRepository.findByRound(round);
    }

    public RoundScore getScoreByRoundAndHoleNum(Round round, int holeNum) {
        return roundScoreRepository.findByRoundAndHoleNum(round, holeNum);
    }

    public RoundScore createRoundScore(RoundScore score) {
        return roundScoreRepository.save(score);
    }

    public List<RoundScore> createMultipleRoundScores(List<RoundScore> scores) {
        return roundScoreRepository.saveAll(scores);
    }
    

    public RoundScore updateRoundScore(int id, RoundScore updatedScore) {
        return roundScoreRepository.findById(id)
                .map(existing -> {
                    existing.setRound(updatedScore.getRound());
                    existing.setHoleNum(updatedScore.getHoleNum());
                    existing.setStrokes(updatedScore.getStrokes());
                    return roundScoreRepository.save(existing);
                })
                .orElse(null);
    }

    public boolean deleteRoundScore(int id) {
        if (roundScoreRepository.existsById(id)) {
            roundScoreRepository.deleteById(id);
            return true;
        }
        return false;
    }
}