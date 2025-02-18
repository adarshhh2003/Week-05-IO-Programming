package com.csvproblems.advancedproblems.csvtostudentobject;


public class Student {
    private String name;
    private int age;
    private double gpa;
    private String email;

    public Student(String name, int age, double gpa, String email) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gpa=" + gpa +
                ", email='" + email + '\'' +
                '}';
    }
}