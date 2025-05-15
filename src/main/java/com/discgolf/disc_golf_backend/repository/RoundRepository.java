package com.discgolf.disc_golf_backend.repository;

import com.discgolf.disc_golf_backend.models.Course;
import com.discgolf.disc_golf_backend.models.Round;
import com.discgolf.disc_golf_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoundRepository extends JpaRepository<Round, Integer> {

    List<Round> findByUser(User user);

    List<Round> findByCourse(Course course);

    List<Round> findByUserAndCourse(User user, Course course);
}