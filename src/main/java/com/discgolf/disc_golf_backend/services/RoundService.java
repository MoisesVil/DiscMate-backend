package com.discgolf.disc_golf_backend.services;

import com.discgolf.disc_golf_backend.models.Course;
import com.discgolf.disc_golf_backend.models.Round;
import com.discgolf.disc_golf_backend.models.User;
import com.discgolf.disc_golf_backend.repository.CourseRepository;
import com.discgolf.disc_golf_backend.repository.RoundRepository;
import com.discgolf.disc_golf_backend.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoundService {

    private final RoundRepository roundRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public RoundService(RoundRepository roundRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.roundRepository = roundRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public List<Round> getAllRounds() {
        return roundRepository.findAll();
    }

    public Optional<Round> getRoundById(int id) {
        return roundRepository.findById(id);
    }

    public List<Round> getRoundsByUser(User user) {
        return roundRepository.findByUser(user);
    }

    public List<Round> getRoundsByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return roundRepository.findByUser(user);
    }

    public List<Round> getRoundsByCourse(Course course) {
        return roundRepository.findByCourse(course);
    }

    public Round createRound(Round round) {
        int userId = round.getUser().getId();
        int courseId = round.getCourse().getId();
    
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
    
        round.setUser(user);
        round.setCourse(course);
    
        return roundRepository.save(round);
    }

    public Round updateRound(int id, Round updatedRound) {
        return roundRepository.findById(id)
                .map(existing -> {
                    existing.setUser(updatedRound.getUser());
                    existing.setCourse(updatedRound.getCourse());
                    existing.setDatePlayed(updatedRound.getDatePlayed());
                    existing.setScores(updatedRound.getScores());
                    return roundRepository.save(existing);
                })
                .orElse(null);
    }

    public boolean deleteRound(int id) {
        if (roundRepository.existsById(id)) {
            roundRepository.deleteById(id);
            return true;
        }
        return false;
    }
}