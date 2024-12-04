package tests;

import controller.StudentController;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

 class StudentControllerTest {
    private StudentController controller;

    @BeforeEach
    public void setUp() {
        // Initialize the StudentController before each test
        controller = new StudentController();
    }

    @Test
     void testAddStudentSuccessfully() {
        // Arrange
        Student student = new Student.StudentBuilder(1)
                .setName("Alice")
                .setAge(20)
                .setGrades(85.0)
                .build();

        // Act
        controller.addStudent(student);

        // Assert
        List<Student> students = controller.getStudents();
        assertEquals(1, students.size(), "The student list size should be 1 after adding a student.");
        assertEquals("Alice", students.get(0).getName(), "The added student should have the name 'Alice'.");
    }

    @Test
     void testAddDuplicateStudent() {
        // Arrange
        Student student1 = new Student.StudentBuilder(1).setName("Alice").build();
        Student student2 = new Student.StudentBuilder(1).setName("Alice").build(); // Same ID as student1

        // Act
        controller.addStudent(student1);
        controller.addStudent(student2); // Should be ignored

        // Assert
        List<Student> students = controller.getStudents();
        assertEquals(1, students.size(), "The student list should contain only unique students.");
        assertEquals("Alice", students.get(0).getName(), "The student's name should be 'Alice'.");
    }

    @Test
     void testUpdateExistingStudent() {
        // Arrange
        Student student = new Student.StudentBuilder(1)
                .setName("Alice")
                .setAge(20)
                .setGrades(85.0)
                .build();
        controller.addStudent(student);

        // Act
        controller.updateStudent("Alice", "Alicia", 21, 90.0);

        // Assert
        Student updatedStudent = controller.getStudents().get(0);
        assertEquals("Alicia", updatedStudent.getName(), "The student's name should be updated to 'Alicia'.");
        assertEquals(21, updatedStudent.getAge(), "The student's age should be updated to 21.");
        assertEquals(90.0, updatedStudent.getGrades(), "The student's grades should be updated to 90.0.");
    }

    @Test
     void testUpdateNonExistentStudent() {
        // Act
        controller.updateStudent("NonExistent", "NewName", 22, 95.0);

        // Assert
        assertTrue(controller.getStudents().isEmpty(), "Updating a non-existent student should not affect the student list.");
    }

    @Test
     void testDeleteExistingStudentByName() {
        // Arrange
        Student student = new Student.StudentBuilder(1)
                .setName("Alice")
                .build();
        controller.addStudent(student);

        // Act
        controller.deleteStudentByName("Alice");

        // Assert
        assertTrue(controller.getStudents().isEmpty(), "The student list should be empty after deleting the only student.");
    }

    @Test
    void testDeleteNonExistentStudentByName() {
        // Act
        controller.deleteStudentByName("NonExistent");

        // Assert
        assertTrue(controller.getStudents().isEmpty(), "Deleting a non-existent student should not affect the student list.");
    }

    @Test
     void testRankStudentsByGrades() {
        // Arrange
        controller.addStudent(new Student.StudentBuilder(1).setName("Alice").setGrades(80.0).build());
        controller.addStudent(new Student.StudentBuilder(2).setName("Bob").setGrades(90.0).build());

        // Act
        controller.rankByGrades();

        // Assert
        List<Student> students = controller.getStudents();
        assertEquals("Bob", students.get(0).getName(), "Students should be ranked by grades in descending order.");
        assertEquals("Alice", students.get(1).getName(), "The second student should be 'Alice' with lower grades.");
    }

    @Test
     void testRankStudentsByAge() {
        // Arrange
        controller.addStudent(new Student.StudentBuilder(1).setName("Alice").setAge(22).build());
        controller.addStudent(new Student.StudentBuilder(2).setName("Bob").setAge(20).build());

        // Act
        controller.rankByAge();

        // Assert
        List<Student> students = controller.getStudents();
        assertEquals("Bob", students.get(0).getName(), "Students should be ranked by age in ascending order.");
        assertEquals("Alice", students.get(1).getName(), "The second student should be 'Alice' with higher age.");
    }

    @Test
     void testSearchExistingStudentByName() {
        // Arrange
        Student student = new Student.StudentBuilder(1).setName("Alice").build();
        controller.addStudent(student);

        // Act
        Student foundStudent = controller.getStudents().stream()
                .filter(s -> s.getName().equalsIgnoreCase("Alice"))
                .findFirst()
                .orElse(null);

        // Assert
        assertNotNull(foundStudent, "The search should return the student with the name 'Alice'.");
        assertEquals("Alice", foundStudent.getName(), "The found student's name should match 'Alice'.");
    }

    @Test
     void testSearchNonExistentStudentByName() {
        // Act
        Student foundStudent = controller.getStudents().stream()
                .filter(s -> s.getName().equalsIgnoreCase("NonExistent"))
                .findFirst()
                .orElse(null);

        // Assert
        assertNull(foundStudent, "Searching for a non-existent student should return null.");
    }
}
