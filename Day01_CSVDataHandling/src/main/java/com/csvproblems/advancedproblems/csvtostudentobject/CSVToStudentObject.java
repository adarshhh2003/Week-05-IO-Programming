package com.csvproblems.advancedproblems.csvtostudentobject;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVToStudentObject {
    public static List<Student> readStudentsFromCSV(String filePath) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip header row
                    continue;
                }

                String[] fields = line.split(",");

                if (fields.length < 4) {
                    System.out.println("Skipping invalid row: " + line);
                    continue;
                }

                try {
                    String name = fields[0].trim();
                    int age = Integer.parseInt(fields[1].trim());
                    double gpa = Double.parseDouble(fields[2].trim());
                    String email = fields[3].trim();

                    students.add(new Student(name, age, gpa, email));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return students;
    }

    public static void main(String[] args) {
        String filePath = "src/main/java/com/csvproblems/advancedproblems/csvtostudentobject/students.csv";
        List<Student> students = readStudentsFromCSV(filePath);

        System.out.println("\n Student List:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}