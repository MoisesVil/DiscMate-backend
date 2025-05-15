package com.discgolf.disc_golf_backend.repository;

import com.discgolf.disc_golf_backend.models.Round;
import com.discgolf.disc_golf_backend.models.RoundScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoundScoreRepository extends JpaRepository<RoundScore, Integer> {

    List<RoundScore> findByRound(Round round);

    RoundScore findByRoundAndHoleNum(Round round, int holeNum);
}