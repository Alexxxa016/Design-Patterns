package tests;

import model.Student;
import model.StudentGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentGroupTest {
    private Student student1;
    private Student student2;
    private Student student3;
    private StudentGroup group1;
    private StudentGroup group2;

    @BeforeEach
    public void setUp() {
        student1 = new Student.StudentBuilder(1).setName("Alice").setGrades(85.0).setAge(20).build();
        student2 = new Student.StudentBuilder(2).setName("Bob").setGrades(90.0).setAge(22).build();
        student3 = new Student.StudentBuilder(3).setName("Charlie").setGrades(80.0).setAge(23).build();

        group1 = new StudentGroup("Group 1");
        group2 = new StudentGroup("Group 2");
    }

    @Test
    public void testAddStudentToGroup() {
        // Act
        group1.addMember(student1);
        group1.addMember(student2);

        // Assert
        assertEquals(2, group1.getMembers().size(), "Group 1 should have 2 members.");
        assertTrue(group1.getMembers().contains(student1), "Group 1 should contain Student 1.");
        assertTrue(group1.getMembers().contains(student2), "Group 1 should contain Student 2.");
    }

    @Test
    public void testAddGroupToGroup() {
        // Arrange
        group1.addMember(student1);
        group2.addMember(student2);
        group2.addMember(student3);

        // Act
        group1.addMember(group2);

        // Assert
        assertEquals(2, group1.getMembers().size(), "Group 1 should have 2 members (1 student, 1 group).");
        assertTrue(group1.getMembers().contains(group2), "Group 1 should contain Group 2 as a member.");
        assertTrue(group2.getMembers().contains(student2), "Group 2 should contain Student 2.");
        assertTrue(group2.getMembers().contains(student3), "Group 2 should contain Student 3.");
    }

    @Test
    public void testDisplayDetails() {
        // Arrange
        group1.addMember(student1);
        group1.addMember(student2);

        // Act
        String details = group1.displayDetails();

        // Assert
        assertTrue(details.contains("Alice"), "Details should include Student 1's name.");
        assertTrue(details.contains("Bob"), "Details should include Student 2's name.");
    }

    @Test
    public void testRemoveStudentFromGroup() {
        // Arrange
        group1.addMember(student1);
        group1.addMember(student2);

        // Act
        group1.removeMember(student1);

        // Assert
        assertEquals(1, group1.getMembers().size(), "Group 1 should have 1 member after removal.");
        assertFalse(group1.getMembers().contains(student1), "Group 1 should no longer contain Student 1.");
    }

    @Test
    public void testRemoveGroupFromGroup() {
        // Arrange
        group1.addMember(group2);
        group2.addMember(student1);

        // Act
        group1.removeMember(group2);

        // Assert
        assertTrue(group1.getMembers().isEmpty(), "Group 1 should have no members after removing Group 2.");
        assertTrue(group2.getMembers().contains(student1), "Group 2 should still contain Student 1.");
    }
}
