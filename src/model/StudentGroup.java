package model;

import java.util.ArrayList;
import java.util.List;

public class StudentGroup {
    private final String groupName;
    private final List<Object> members; // Can store both Students and StudentGroups

    public StudentGroup(String groupName) {
        this.groupName = groupName;
        this.members = new ArrayList<>();
    }

    // Getter for the group name
    public String getGroupName() {
        return groupName;
    }

    // Getter for members
    public List<Object> getMembers() {
        return new ArrayList<>(members); // Return a copy to avoid external modification
    }

    // Add a student or another group to the members list
    public void addMember(Object member) {
        if (member instanceof Student || member instanceof StudentGroup) {
            members.add(member);
        } else {
            throw new IllegalArgumentException("Member must be either a Student or a StudentGroup.");
        }
    }

    // Remove a student or another group from the members list
    public void removeMember(Object member) {
        if (!members.remove(member)) {
            System.out.println("Member not found in the group: " + member);
        }
    }

    // Display details of the group and its members
    public String displayDetails() {
        StringBuilder details = new StringBuilder("Group: " + groupName + "\n");
        for (Object member : members) {
            if (member instanceof Student) {
                details.append("  Student: ").append(member.toString()).append("\n");
            } else if (member instanceof StudentGroup) {
                details.append("  Subgroup: ").append(((StudentGroup) member).getGroupName()).append("\n");
                details.append(((StudentGroup) member).displayDetails()); // Recursive call
            }
        }
        return details.toString();
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "groupName='" + groupName + '\'' +
                ", members=" + members +
                '}';
    }
}
