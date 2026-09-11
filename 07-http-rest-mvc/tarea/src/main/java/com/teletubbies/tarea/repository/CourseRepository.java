package com.teletubbies.tarea.repository;

import com.teletubbies.tarea.model.Course;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {

    private final Map<String, Course> courses = new LinkedHashMap<>();

    public Course save(Course course) {
        courses.put(course.getId(), course);
        return course;
    }

    public Optional<Course> findById(String id) {
        return Optional.ofNullable(courses.get(id));
    }

    public List<Course> findByInstructor(String instructor) {
        List<Course> all = new ArrayList<>(courses.values());
        if (instructor == null || instructor.isBlank()) {
            return all;
        }
        return all.stream()
                .filter(c -> instructor.equalsIgnoreCase(c.getInstructor()))
                .collect(Collectors.toList());
    }

    public void deleteById(String id) {
        courses.remove(id);
    }

    public boolean existsById(String id) {
        return courses.containsKey(id);
    }
}