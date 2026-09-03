package org.example;

import java.util.Arrays;
import java.util.List;

/** Same scenario as grade-calculator-app, now handled explicitly. */
public class Main {
    public static void main(String[] args) {
        Student ana = new Student("Ana", Arrays.asList(7.0, 8.0, 9.0));
        Student luis = new Student("Luis", Arrays.asList(6.0, 6.0, 6.0));   // exactly at the passing grade
        Student mara = new Student("Mara", null);                          // enrolled, no grades loaded yet

        List<Student> group = Arrays.asList(ana, luis, mara);

        GradeCalculator calculator = new GradeCalculator();

        System.out.println("Class average: " + calculator.averageOfClass(group));   // ignores Mara -> 7.0
        System.out.println("Students passing: " + calculator.countPassing(group));  // Ana and Luis -> 2

        try {
            calculator.averageOf(mara);
        } catch (NoGradesException e) {
            System.out.println("Expected: " + e.getMessage());
        }
    }
}
