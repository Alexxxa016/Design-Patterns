package tests;

import model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testStudentBuilderSuccessfully() {
        // Arrange & Act
        Student student = new Student.StudentBuilder(1)
                .setName("Alice")
                .setAge(20)
                .setGrades(85.0)
                .build();

        // Assert
        assertEquals(1, student.getStudentId(), "The student ID should match the value set by the builder.");
        assertEquals("Alice", student.getName(), "The student's name should match the value set by the builder.");
        assertEquals(20, student.getAge(), "The student's age should match the value set by the builder.");
        assertEquals(85.0, student.getGrades(), "The student's grades should match the value set by the builder.");
    }

    @Test
    public void testPartialStudentBuilder() {
        // Arrange & Act
        Student student = new Student.StudentBuilder(2)
                .setName("Bob")
                .build(); // Only ID and name are set

        // Assert
        assertEquals(2, student.getStudentId(), "The student ID should match the value set by the builder.");
        assertEquals("Bob", student.getName(), "The student's name should match the value set by the builder.");
        assertEquals(0, student.getAge(), "The student's age should default to 0 if not set.");
        assertEquals(0.0, student.getGrades(), "The student's grades should default to 0.0 if not set.");
    }

    @Test
    public void testStudentEquality() {
        // Arrange
        Student student1 = new Student.StudentBuilder(1)
                .setName("Alice")
                .setAge(20)
                .setGrades(85.0)
                .build();

        Student student2 = new Student.StudentBuilder(1)
                .setName("Alice")
                .setAge(20)
                .setGrades(85.0)
                .build();

        // Assert
        assertEquals(student1, student2, "Two students with the same attributes should be considered equal.");
    }

    @Test
    public void testStudentToString() {
        // Arrange
        Student student = new Student.StudentBuilder(3)
                .setName("Charlie")
                .setAge(22)
                .setGrades(75.0)
                .build();

        // Act
        String studentString = student.toString();

        // Assert
        assertTrue(studentString.contains("Charlie"), "The string representation should contain the student's name.");
        assertTrue(studentString.contains("22"), "The string representation should contain the student's age.");
        assertTrue(studentString.contains("75.0"), "The string representation should contain the student's grades.");
    }
}
