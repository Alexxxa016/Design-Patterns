package tests;

import model.Address;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.RankingStrategy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RankingStrategyTest {
    private List<Student> students;

    @BeforeEach
    public void setUp() {
        students = new ArrayList<>();
        students.add(new Student.StudentBuilder(1).setName("Alice").setGrades(85.0).setAge(22).build());
        students.add(new Student.StudentBuilder(2).setName("Bob").setGrades(90.0).setAge(20).build());
        students.add(new Student.StudentBuilder(3).setName("Charlie").setGrades(80.0).setAge(23).build());
    }

    @Test
    public void testRankByGrades() {
        // Arrange
        RankingStrategy rankByGrades = new RankingStrategy.RankByGrades();

        // Act
        rankByGrades.rank(students);

        // Assert
        assertEquals("Bob", students.get(0).getName(), "The student with the highest grades should be ranked first.");
        assertEquals("Alice", students.get(1).getName(), "The student with the second highest grades should be ranked second.");
        assertEquals("Charlie", students.get(2).getName(), "The student with the lowest grades should be ranked last.");
    }

    @Test
    public void testRankByAge() {
        // Arrange
        RankingStrategy rankByAge = new RankingStrategy.RankByAge();

        // Act
        rankByAge.rank(students);

        // Assert
        assertEquals("Bob", students.get(0).getName(), "The youngest student should be ranked first.");
        assertEquals("Alice", students.get(1).getName(), "The second youngest student should be ranked second.");
        assertEquals("Charlie", students.get(2).getName(), "The oldest student should be ranked last.");
    }

    @Test
    public void testEmptyList() {
        // Arrange
        List<Student> emptyList = new ArrayList<>();
        RankingStrategy rankByGrades = new RankingStrategy.RankByGrades();
        RankingStrategy rankByAge = new RankingStrategy.RankByAge();

        // Act
        rankByGrades.rank(emptyList);
        rankByAge.rank(emptyList);

        // Assert
        assertTrue(emptyList.isEmpty(), "Ranking an empty list should leave the list empty.");
    }

    public static class AddressTest {

        @Test
        public void testAddressBuilderSuccessfully() {
            // Arrange & Act
            Address address = new Address.AddressBuilder()
                    .setStreet("123 Main St")
                    .setCity("Springfield")
                    .setZipCode("12345")
                    .build();

            // Assert
            assertEquals("123 Main St", address.getStreet(), "The street should match the value set by the builder.");
            assertEquals("Springfield", address.getCity(), "The city should match the value set by the builder.");
            assertEquals("12345", address.getZipCode(), "The zip code should match the value set by the builder.");
        }

        @Test
        public void testPartialAddressBuilder() {
            // Arrange & Act
            Address address = new Address.AddressBuilder()
                    .setStreet("123 Main St")
                    .setCity("Springfield")
                    .build(); // Zip code is not set

            // Assert
            assertEquals("123 Main St", address.getStreet(), "The street should match the value set by the builder.");
            assertEquals("Springfield", address.getCity(), "The city should match the value set by the builder.");
            assertNull(address.getZipCode(), "The zip code should be null if not set.");
        }

        @Test
        public void testUpdateAddressFields() {
            // Arrange
            Address address = new Address.AddressBuilder()
                    .setStreet("123 Main St")
                    .setCity("Springfield")
                    .setZipCode("12345")
                    .build();

            // Act
            Address updatedAddress = new Address.AddressBuilder()
                    .setStreet("456 Elm St")
                    .setCity("Shelbyville")
                    .setZipCode("67890")
                    .build();

            // Assert
            assertNotEquals(address.getStreet(), updatedAddress.getStreet(), "The street should be updated.");
            assertNotEquals(address.getCity(), updatedAddress.getCity(), "The city should be updated.");
            assertNotEquals(address.getZipCode(), updatedAddress.getZipCode(), "The zip code should be updated.");
        }

        @Test
        public void testAddressEquality() {
            // Arrange
            Address address1 = new Address.AddressBuilder()
                    .setStreet("123 Main St")
                    .setCity("Springfield")
                    .setZipCode("12345")
                    .build();

            Address address2 = new Address.AddressBuilder()
                    .setStreet("123 Main St")
                    .setCity("Springfield")
                    .setZipCode("12345")
                    .build();

            // Assert
            assertEquals(address1.getStreet(), address2.getStreet(), "The street should be equal.");
            assertEquals(address1.getCity(), address2.getCity(), "The city should be equal.");
            assertEquals(address1.getZipCode(), address2.getZipCode(), "The zip code should be equal.");
        }
    }
}
