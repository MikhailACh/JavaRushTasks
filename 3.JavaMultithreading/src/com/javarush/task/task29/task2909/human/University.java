package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student s : students)
            if (s.getAverageGrade() == averageGrade)
                return s;

        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        double maxGrade = 0d;

        for (Student s : students)
            if (s.getAverageGrade() > maxGrade)
                maxGrade = s.getAverageGrade();

        for (Student s : students)
            if (s.getAverageGrade() == maxGrade)
                return s;

        return null;
    }

    public Student getStudentWithMinAverageGrade() {
        double minGrade = Double.MAX_VALUE;

        for (Student s : students)
            if (s.getAverageGrade() < minGrade)
                minGrade = s.getAverageGrade();

        for (Student s : students)
            if (s.getAverageGrade() == minGrade)
                return s;

        return null;
    }

    public void expel(Student student) {
        students.remove(student);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}