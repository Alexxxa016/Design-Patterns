package utils;

import model.Student;

import java.util.Comparator;
import java.util.List;

public interface RankingStrategy {
    void rank(List<Student> students);

    class RankByGrades implements RankingStrategy {
        @Override
        public void rank(List<Student> students) {
            students.sort(Comparator.comparingDouble(Student::getGrades).reversed());
        }
    }

    class RankByAge implements RankingStrategy {
        @Override
        public void rank(List<Student> students) {
            students.sort(Comparator.comparingInt(Student::getAge));
        }
    }
}
