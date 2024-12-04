package controller;

import model.Student;
import view.Observer;
import model.StudentGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentController {
    private final List<Student> students = new ArrayList<>();
    private final List<StudentGroup> groups = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();

    // Add an observer
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Notify all observers
    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // Add a student
    public void addStudent(Student student) {
        if (students.stream().anyMatch(s -> s.getStudentId() == student.getStudentId())) {
            notifyObservers("Error: Student with ID " + student.getStudentId() + " already exists.");
            return;
        }
        students.add(student);
        notifyObservers("Added student: " + student.getName());
    }

    // Update a student
    public void updateStudent(String name, String newName, Integer age, Double grades) {
        Student student = students.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (student != null) {
            student.update(newName, age, grades, null);
            notifyObservers("Updated student: " + (newName != null ? newName : name));
        } else {
            notifyObservers("Error: No student found with name: " + name);
        }
    }

    // Delete a student by name
    public void deleteStudentByName(String name) {
        Student student = students.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (student != null) {
            students.remove(student);
            notifyObservers("Deleted student: " + name);
        } else {
            notifyObservers("Error: No student found with name: " + name);
        }
    }

    // Add a group
    public void addGroup(StudentGroup group) {
        if (groups.stream().anyMatch(g -> g.getGroupName().equalsIgnoreCase(group.getGroupName()))) {
            notifyObservers("Error: Group with name " + group.getGroupName() + " already exists.");
            return;
        }
        groups.add(group);
        notifyObservers("Added group: " + group.getGroupName());
    }

    // Add a student to a group
    public void addStudentToGroup(String groupName, Student student) {
        StudentGroup group = groups.stream()
                .filter(g -> g.getGroupName().equalsIgnoreCase(groupName))
                .findFirst()
                .orElse(null);

        if (group != null) {
            group.addMember(student);
            notifyObservers("Added student " + student.getName() + " to group: " + groupName);
        } else {
            notifyObservers("Error: Group not found: " + groupName);
        }
    }

    // Get all groups
    public List<StudentGroup> getGroups() {
        return new ArrayList<>(groups); // Defensive copy
    }

    // Rank students by grades
    public void rankByGrades() {
        students.sort(Comparator.comparingDouble(Student::getGrades).reversed());
        notifyObservers("Students ranked by grades.");
    }

    // Rank students by age
    public void rankByAge() {
        students.sort(Comparator.comparingInt(Student::getAge));
        notifyObservers("Students ranked by age.");
    }

    // Get all students
    public List<Student> getStudents() {
        return new ArrayList<>(students); // Defensive copy
    }


}
