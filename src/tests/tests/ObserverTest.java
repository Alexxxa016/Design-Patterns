package tests;

import controller.StudentController;
import model.Student;
import model.StudentGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ConsoleView;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObserverTest {
    private StudentController controller;
    private ByteArrayOutputStream output;

    @BeforeEach
    public void setUp() {
        controller = new StudentController();
        ConsoleView view = new ConsoleView();
        controller.addObserver(view);

        // Redirect console output for capturing notifications
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    public void testObserverNotifiedOnAddStudent() {
        // Arrange
        Student student = new Student.StudentBuilder(1)
                .setName("Alice")
                .setAge(20)
                .setGrades(85.0)
                .build();

        // Act
        controller.addStudent(student);

        // Assert
        String consoleOutput = output.toString().trim();
        assertTrue(consoleOutput.contains("Notification: Added student: Alice"),
                "The observer should be notified with the message: 'Added student: Alice'");
    }

    @Test
    public void testObserverNotifiedOnUpdateStudent() {
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
        String consoleOutput = output.toString().trim();
        assertTrue(consoleOutput.contains("Notification: Updated student: Alicia"),
                "The observer should be notified with the message: 'Updated student: Alicia'");
    }

    @Test
    public void testObserverNotifiedOnDeleteStudent() {
        // Arrange
        Student student = new Student.StudentBuilder(1)
                .setName("Alice")
                .setAge(20)
                .setGrades(85.0)
                .build();
        controller.addStudent(student);

        // Act
        controller.deleteStudentByName("Alice");

        // Assert
        String consoleOutput = output.toString().trim();
        assertTrue(consoleOutput.contains("Notification: Deleted student: Alice"),
                "The observer should be notified with the message: 'Deleted student: Alice'");
    }

    @Test
    public void testObserverNotifiedOnAddGroup() {
        // Arrange
        StudentGroup group = new StudentGroup("Math Group");

        // Act
        controller.addGroup(group);

        // Assert
        String consoleOutput = output.toString().trim();
        assertTrue(consoleOutput.contains("Notification: Added group: Math Group"),
                "The observer should be notified with the message: 'Added group: Math Group'");
    }

    @Test
    public void testObserverNotifiedOnAddStudentToGroup() {
        // Arrange
        Student student = new Student.StudentBuilder(1)
                .setName("Alice")
                .build();
        StudentGroup group = new StudentGroup("Science Group");
        controller.addGroup(group);

        // Act
        controller.addStudentToGroup("Science Group", student);

        // Assert
        String consoleOutput = output.toString().trim();
        assertTrue(consoleOutput.contains("Notification: Added student Alice to group: Science Group"),
                "The observer should be notified with the message: 'Added student Alice to group: Science Group'");
    }
}
