package com.teletubbies.tarea.repository;

import com.teletubbies.tarea.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class EnrollmentRepository {

    private final Map<String, Enrollment> enrollments = new LinkedHashMap<>();

    public Enrollment save(Enrollment enrollment) {
        enrollments.put(enrollment.getId(), enrollment);
        return enrollment;
    }
}