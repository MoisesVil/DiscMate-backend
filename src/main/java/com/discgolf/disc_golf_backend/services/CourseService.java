package com.discgolf.disc_golf_backend.services;

import com.discgolf.disc_golf_backend.models.Course;
import com.discgolf.disc_golf_backend.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(int id) {
        return courseRepository.findById(id);
    }

    public Course getCourseByName(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(int id, Course updatedCourse) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    existingCourse.setCourseName(updatedCourse.getCourseName());
                    existingCourse.setNumHoles(updatedCourse.getNumHoles());
                    existingCourse.setLat(updatedCourse.getLat());
                    existingCourse.setLng(updatedCourse.getLng());
                    return courseRepository.save(existingCourse);
                })
                .orElseGet(() -> {
                    updatedCourse.setId(id);
                    return courseRepository.save(updatedCourse);
                });
    }

    public boolean deleteCourse(int id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}