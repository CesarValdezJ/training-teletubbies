package org.example;

import java.util.ArrayList;
import java.util.List;

/** A student and the list of numeric grades they earned during the term. */
public class Student {
    private final String name;
    private final List<Double> grades;

    public Student(String name, List<Double> grades) {
        this.name = name;
        // Normalize null to an empty list so no caller ever has to deal with null grades.
        this.grades = (grades == null) ? new ArrayList<>() : new ArrayList<>(grades);
    }

    public String getName() {
        return name;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public boolean hasGrades() {
        return !grades.isEmpty();
    }
}
