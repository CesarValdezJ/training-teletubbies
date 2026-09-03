package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Test class we grow during the guided session.
 *
 * It starts with one passing test. During the walkthrough we add:
 *  - averageOf_throwsWhenStudentHasNoGrades          (assertThrows)
 *  - countPassing_countsStudentExactlyAtPassingGrade
 *  - averageOfClass_ignoresStudentsWithNoGrades
 * See ../../grade-calculator-app-solucion for the finished version.
 */
class GradeCalculatorTest {

    private final GradeCalculator calculator = new GradeCalculator();

    @Test
    void averageOf_returnsMeanOfGrades() {
        // Arrange
        Student ana = new Student("Ana", Arrays.asList(7.0, 8.0, 9.0));
        // Act
        double average = calculator.averageOf(ana);
        // Assert
        assertEquals(8.0, average, 1e-9);
    }
}
