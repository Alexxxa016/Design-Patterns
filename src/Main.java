import controller.StudentController;
import model.Student;
import model.Address;
import model.StudentGroup;
import view.ConsoleView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView(); //Observer creation
        StudentController controller = new StudentController();//creating the observable
        controller.addObserver(view);//connecting the observer and observable

        while (true) {
            view.showMessage("\nMenu:");
            view.showMessage("1. Add Student");
            view.showMessage("2. Update Student");
            view.showMessage("3. Delete Student");
            view.showMessage("4. Rank Students");
            view.showMessage("5. Search Student");
            view.showMessage("6. View All Students");
            view.showMessage("7. Add Student Group");
            view.showMessage("8. Add Student to Group");
            view.showMessage("9. Display Group Details");
            view.showMessage("10. Exit");

            int choice = Integer.parseInt(view.getInput("Enter your choice: "));

            switch (choice) {
                case 1 -> {
                    // Add Student
                    int id = Integer.parseInt(view.getInput("Enter Student ID: "));
                    String name = view.getInput("Enter Student Name: ");
                    int age = Integer.parseInt(view.getInput("Enter Student Age: "));
                    double grades = Double.parseDouble(view.getInput("Enter Student Grades: "));
                    String street = view.getInput("Enter Address Street: ");
                    String city = view.getInput("Enter Address City: ");
                    String zipCode = view.getInput("Enter Address Zip Code: ");

                    Address address = new Address.AddressBuilder()
                            .setStreet(street)
                            .setCity(city)
                            .setZipCode(zipCode)
                            .build();

                    Student student = new Student.StudentBuilder(id)
                            .setName(name)
                            .setAge(age)
                            .setGrades(grades)
                            .setAddress(address)
                            .build();

                    controller.addStudent(student);
                }
                case 2 -> {
                    // Update Student
                    String name = view.getInput("Enter the name of the student to update: ");
                    String newName = view.getInput("Enter new name (or press Enter to skip): ");
                    Integer newAge = null;
                    Double newGrades = null;

                    if (!view.getInput("Update age? (y/n): ").equalsIgnoreCase("n")) {
                        newAge = Integer.parseInt(view.getInput("Enter new age: "));
                    }
                    if (!view.getInput("Update grades? (y/n): ").equalsIgnoreCase("n")) {
                        newGrades = Double.parseDouble(view.getInput("Enter new grades: "));
                    }

                    controller.updateStudent(name, newName.isEmpty() ? null : newName, newAge, newGrades);
                }
                case 3 -> {
                    // Delete Student
                    String name = view.getInput("Enter the name of the student to delete: ");
                    Student student = controller.getStudents().stream()
                            .filter(s -> s.getName().equalsIgnoreCase(name))
                            .findFirst()
                            .orElse(null);

                    if (student != null) {
                        view.showMessage("Student Details: " + student);
                        if (view.getInput("Confirm deletion? (y/n): ").equalsIgnoreCase("y")) {
                            controller.deleteStudentByName(name);
                        } else {
                            view.showMessage("Deletion canceled.");
                        }
                    } else {
                        view.showMessage("Student not found.");
                    }
                }
                case 4 -> {
                    // Rank Students
                    view.showMessage("How would you like to rank?");
                    view.showMessage("1. By Grades");
                    view.showMessage("2. By Age");
                    int rankChoice = Integer.parseInt(view.getInput("Enter your choice: "));

                    if (rankChoice == 1) {
                        controller.rankByGrades();
                        view.showMessage("Students ranked by grades:");
                    } else if (rankChoice == 2) {
                        controller.rankByAge();
                        view.showMessage("Students ranked by age:");
                    } else {
                        view.showMessage("Invalid choice.");
                        continue;
                    }

                    List<Student> rankedStudents = controller.getStudents();
                    for (Student student : rankedStudents) {
                        view.showMessage(student.toString());
                    }
                }
                case 5 -> {
                    // Search Student
                    String name = view.getInput("Enter the name of the student to search: ");
                    Student student = controller.getStudents().stream()
                            .filter(s -> s.getName().equalsIgnoreCase(name))
                            .findFirst()
                            .orElse(null);

                    if (student != null) {
                        view.showMessage("Student Details: " + student);
                    } else {
                        view.showMessage("Student not found.");
                    }
                }
                case 6 -> {
                    // View All Students
                    List<Student> students = controller.getStudents();
                    students.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));

                    if (students.isEmpty()) {
                        view.showMessage("No students available.");
                    } else {
                        view.showMessage("All Students:");
                        for (Student student : students) {
                            view.showMessage(student.toString());
                        }
                    }
                }
                case 7 -> {
                    // Add Student Group
                    String groupName = view.getInput("Enter Group Name: ");
                    StudentGroup group = new StudentGroup(groupName);
                    controller.addGroup(group);
                    view.showMessage("Group " + groupName + " added successfully.");
                }
                case 8 -> {
                    // Add Student to Group
                    String groupName = view.getInput("Enter Group Name: ");
                    String studentName = view.getInput("Enter Student Name: ");
                    Student student = controller.getStudents().stream()
                            .filter(s -> s.getName().equalsIgnoreCase(studentName))
                            .findFirst()
                            .orElse(null);

                    if (student != null) {
                        controller.addStudentToGroup(groupName, student);
                        view.showMessage("Student " + studentName + " added to group " + groupName + ".");
                    } else {
                        view.showMessage("Student not found.");
                    }
                }
                case 9 -> {
                    // Display Group Details
                    String groupName = view.getInput("Enter Group Name: ");
                    StudentGroup group = controller.getGroups().stream()
                            .filter(g -> g.getGroupName().equalsIgnoreCase(groupName))
                            .findFirst()
                            .orElse(null);

                    if (group != null) {
                        group.displayDetails();
                    } else {
                        view.showMessage("Group not found.");
                    }
                }
                case 10 -> {
                    // Exit
                    view.showMessage("Exiting...");
                    return;
                }
                default -> view.showMessage("Invalid choice. Please try again.");
            }
        }
    }
}
