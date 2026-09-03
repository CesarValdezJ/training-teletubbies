package org.example;

import java.util.Arrays;
import java.util.List;

/** Reproduces the failure: run this and read the stack trace. */
public class Main {
    public static void main(String[] args) {
        Student ana = new Student("Ana", Arrays.asList());
        Student luis = new Student("Luis", Arrays.asList(6.0, 6.0, 6.0));   // exactly at the passing grade
        //Student mara = new Student("Mara", null);                          // enrolled, no grades loaded yet

        List<Student> group = Arrays.asList(ana, luis);

        GradeCalculator calculator = new GradeCalculator();

        System.out.println("Class average: " + calculator.averageOfClass(group));
        System.out.println("Students passing: " + calculator.countPassing(group));
    }
}
