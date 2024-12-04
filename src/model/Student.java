package model;

import java.util.Objects;

public class Student {
    private final int studentId;
    private String name;
    private int age;
    private double grades;
    private Address address; // Composite object

    private Student(StudentBuilder builder) {
        this.studentId = builder.studentId;
        this.name = builder.name;
        this.age = builder.age;
        this.grades = builder.grades;
        this.address = builder.address;
    }

    // Getters
    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGrades() {
        return grades;
    }

    public Address getAddress() {
        return address;
    }

    // Update method for mutability
    public void update(String name, Integer age, Double grades, Address address) {
        if (name != null) this.name = name;
        if (age != null) this.age = age;
        if (grades != null) this.grades = grades;
        if (address != null) this.address = address;
    }

    @Override
    public String toString() {
        return "ID: " + studentId + ", Name: " + name + ", Age: " + age + ", Grades: " + grades
                + ", Address: " + (address != null ? address : "N/A");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if references are the same
        if (obj == null || getClass() != obj.getClass()) return false; // Null or class mismatch

        Student student = (Student) obj;

        return studentId == student.studentId &&
                age == student.age &&
                Double.compare(student.grades, grades) == 0 &&
                Objects.equals(name, student.name) &&
                Objects.equals(address, student.address); // Use Objects.equals for null safety
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, name, age, grades, address); // Use Objects.hash for consistent hashing
    }

    public static class StudentBuilder {
        private final int studentId; // Required field
        private String name;
        private int age;
        private double grades;
        private Address address;

        public StudentBuilder(int studentId) {
            this.studentId = studentId;
        }

        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public StudentBuilder setGrades(double grades) {
            this.grades = grades;
            return this;
        }

        public StudentBuilder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
