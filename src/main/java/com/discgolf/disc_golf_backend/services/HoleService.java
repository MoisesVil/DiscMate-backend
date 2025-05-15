package com.discgolf.disc_golf_backend.services;

import com.discgolf.disc_golf_backend.models.Course;
import com.discgolf.disc_golf_backend.models.Hole;
import com.discgolf.disc_golf_backend.repository.HoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoleService {

    private final HoleRepository holeRepository;

    public HoleService(HoleRepository holeRepository) {
        this.holeRepository = holeRepository;
    }

    public List<Hole> getAllHoles() {
        return holeRepository.findAll();
    }

    public Optional<Hole> getHoleById(int id) {
        return holeRepository.findById(id);
    }

    public List<Hole> getHolesByCourse(Course course) {
        return holeRepository.findByCourse(course);
    }

    public Hole getHoleByCourseAndHoleNum(Course course, int holeNum) {
        return holeRepository.findByCourseAndHoleNum(course, holeNum);
    }

    public Hole createHole(Hole hole) {
        return holeRepository.save(hole);
    }

    public Hole updateHole(int id, Hole updatedHole) {
        return holeRepository.findById(id)
                .map(existing -> {
                    existing.setHoleNum(updatedHole.getHoleNum());
                    existing.setPar(updatedHole.getPar());
                    existing.setDistance(updatedHole.getDistance());
                    existing.setTeepadLat(updatedHole.getTeepadLat());
                    existing.setTeepadLng(updatedHole.getTeepadLng());
                    existing.setBasketLat(updatedHole.getBasketLat());
                    existing.setBasketLng(updatedHole.getBasketLng());
                    return holeRepository.save(existing);
                })
                .orElseGet(() -> {
                    updatedHole.setId(id);
                    return holeRepository.save(updatedHole);
                });
    }

    public boolean deleteHole(int id) {
        if (holeRepository.existsById(id)) {
            holeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}