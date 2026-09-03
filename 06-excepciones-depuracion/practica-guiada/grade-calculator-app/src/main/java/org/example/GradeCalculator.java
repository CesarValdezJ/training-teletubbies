package org.example;

import java.util.List;

/**
 * Computes term statistics for a group of students.
 *
 * NOTE (for the session): this class ships with three planted defects.
 * They are fixed together during the guided walkthrough.
 */
public class GradeCalculator {

    /** Minimum grade required to pass. */
    public static final double PASSING_GRADE = 6.0;

    /** Average grade of a single student. */
    public double averageOf(Student student) {
        double sum = 0;
        // DEFECT 1: getGrades() can be null (a student with no grades loaded yet) -> NullPointerException.
        for (double grade : student.getGrades()) {
            sum += grade;
        }
        // DEFECT 2: an empty grade list divides by zero -> NaN / Infinity.
        return sum / student.getGrades().size();
    }

    /** Average of the individual averages of every student in the group. */
    public double averageOfClass(List<Student> students) {
        double sum = 0;
        for (Student student : students) {
            sum += averageOf(student);
        }
        return sum / students.size();
    }

    /** How many students reached the passing grade. */
    public int countPassing(List<Student> students) {
        int passing = 0;
        for (Student student : students) {
            // DEFECT 3: must be >=. A student exactly at PASSING_GRADE is currently counted as failing.
            if (averageOf(student) > PASSING_GRADE) {
                passing++;
            }
        }
        return passing;
    }
}
