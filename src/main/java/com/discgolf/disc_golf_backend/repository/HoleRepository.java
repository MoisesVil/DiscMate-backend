package com.discgolf.disc_golf_backend.repository;

import com.discgolf.disc_golf_backend.models.Hole;
import com.discgolf.disc_golf_backend.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoleRepository extends JpaRepository<Hole, Integer> {

    // Find all holes for a given course
    List<Hole> findByCourse(Course course);

    // Find a hole by course and hole number
    Hole findByCourseAndHoleNum(Course course, int holeNum);
}