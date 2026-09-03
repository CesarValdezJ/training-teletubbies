package org.example;

import java.util.List;

/**
 * Computes term statistics for a group of students.
 *
 * Reference solution. The three original defects were:
 *  1. averageOf(): iterated getGrades() without a null check -> NullPointerException for a
 *     student with no grades loaded yet.
 *  2. averageOf(): divided by getGrades().size() -> division by zero (NaN) on an empty list.
 *  3. countPassing(): used `> PASSING_GRADE`, so a student exactly at the passing grade was
 *     counted as failing. Must be `>=`.
 *
 * Error contract:
 *  - averageOf() on a student with no grades throws NoGradesException.
 *  - averageOfClass() / countPassing() skip students with no grades instead of failing;
 *    averageOfClass() throws NoGradesException only if nobody in the group has grades.
 */
public class GradeCalculator {

    /** Minimum grade required to pass. */
    public static final double PASSING_GRADE = 6.0;

    /** Average grade of a single student. Throws if the student has no grades. */
    public double averageOf(Student student) {
        if (!student.hasGrades()) {                       // fix 1 + 2: guard clause, explicit contract
            throw new NoGradesException(student.getName());
        }
        double sum = 0;
        for (double grade : student.getGrades()) {
            sum += grade;
        }
        return sum / student.getGrades().size();
    }

    /** Average of the individual averages, ignoring students with no grades. */
    public double averageOfClass(List<Student> students) {
        double sum = 0;
        int counted = 0;
        for (Student student : students) {
            if (!student.hasGrades()) {
                continue;
            }
            sum += averageOf(student);
            counted++;
        }
        if (counted == 0) {
            throw new NoGradesException("<group>");
        }
        return sum / counted;
    }

    /** How many students reached the passing grade. */
    public int countPassing(List<Student> students) {
        int passing = 0;
        for (Student student : students) {
            if (!student.hasGrades()) {
                continue;
            }
            if (averageOf(student) >= PASSING_GRADE) {   // fix 3: a student exactly at the passing grade passes
                passing++;
            }
        }
        return passing;
    }
}
