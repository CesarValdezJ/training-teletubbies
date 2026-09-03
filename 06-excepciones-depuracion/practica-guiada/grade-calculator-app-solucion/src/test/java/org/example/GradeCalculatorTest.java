package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

/** Finished version of the test class we grow during the guided session. */
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

    @Test
    void averageOf_throwsWhenStudentHasNoGrades() {   // defect 1 + 2
        Student mara = new Student("Mara", null);
        assertThrows(NoGradesException.class, () -> calculator.averageOf(mara));
    }

    @Test
    void countPassing_countsStudentExactlyAtPassingGrade() {   // defect 3
        Student luis = new Student("Luis", Arrays.asList(6.0, 6.0, 6.0));   // average == PASSING_GRADE
        int passing = calculator.countPassing(Collections.singletonList(luis));
        assertEquals(1, passing, "a student exactly at the passing grade must count as passing");
    }

    @Test
    void averageOfClass_ignoresStudentsWithNoGrades() {
        Student ana = new Student("Ana", Arrays.asList(8.0, 8.0));
        Student mara = new Student("Mara", null);
        double average = calculator.averageOfClass(Arrays.asList(ana, mara));
        assertEquals(8.0, average, 1e-9);
    }
}
