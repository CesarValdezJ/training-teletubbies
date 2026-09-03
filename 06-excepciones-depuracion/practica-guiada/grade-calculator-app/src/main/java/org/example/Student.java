package org.example;

import java.util.List;

/** A student and the list of numeric grades they earned during the term. */
public class Student {
    private final String name;
    private final List<Double> grades;

    public Student(String name, List<Double> grades) {
        this.name = name;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public List<Double> getGrades() {
        return grades;
    }
}
