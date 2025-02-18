package com.csvproblems.advancedproblems.validaterecords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ValidateRecords {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PHONE_REGEX = "^\\d{10}$";

    public static void sortRecordBySalary(String filePath) {
        List<String[]> validRecords = new ArrayList<>();
        List<String> invalidRecords = new ArrayList<>();
        String header = "";

        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        Pattern phonePattern = Pattern.compile(PHONE_REGEX);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    header = line;
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(",");

                if (fields.length < 4) { // Expecting at least Name, Department, Salary, Email, Phone
                    invalidRecords.add("Invalid row (missing fields): " + line);
                    continue;
                }

                String email = fields[3].trim();
                String phone = fields.length > 4 ? fields[4].trim() : "";

                // Validate email and phone
                if (!emailPattern.matcher(email).matches()) {
                    invalidRecords.add("Invalid Email: " + line);
                    continue;
                }
                if (!phonePattern.matcher(phone).matches()) {
                    invalidRecords.add("Invalid Phone Number: " + line);
                    continue;
                }

                validRecords.add(fields);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Print header & top 5 valid records
        System.out.println("\n✅ Valid Records (Sorted by Salary):");
        System.out.println(header);
        for (int i = 0; i < Math.min(5, validRecords.size()); i++) {
            System.out.println(String.join(",", validRecords.get(i)));
        }

        // Print invalid records
        if (!invalidRecords.isEmpty()) {
            System.out.println("\n❌ Invalid Records Found:");
            for (String error : invalidRecords) {
                System.out.println(error);
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "src/main/java/com/csvproblems/advancedproblems/validaterecords/employees.csv";
        sortRecordBySalary(filePath);
    }
}
