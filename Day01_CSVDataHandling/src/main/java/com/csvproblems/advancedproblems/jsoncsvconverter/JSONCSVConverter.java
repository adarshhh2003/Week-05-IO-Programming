package com.csvproblems.advancedproblems.jsoncsvconverter;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JSONCSVConverter {
    private static final String JSON_FILE = "src/main/java/com/csvproblems/advancedproblems/jsoncsvconverter/file.json";
    private static final String CSV_FILE = "src/main/java/com/csvproblems/advancedproblems/jsoncsvconverter/file.csv";
    private static final String OUTPUT_JSON_FILE = "src/main/java/com/csvproblems/advancedproblems/jsoncsvconverter/output.json";

    // Convert JSON to CSV
    public static void jsonToCsv() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read JSON file into List of Maps
            List<Map<String, Object>> students = objectMapper.readValue(new File(JSON_FILE), new TypeReference<>() {});

            if (students.isEmpty()) {
                System.out.println("No data found in JSON file.");
                return;
            }

            // Write CSV
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
                // Write Header
                String header = String.join(",", students.get(0).keySet());
                writer.write(header + "\n");

                // Write Data
                for (Map<String, Object> student : students) {
                    String row = student.values().stream()
                            .map(String::valueOf)
                            .reduce((a, b) -> a + "," + b)
                            .orElse("");
                    writer.write(row + "\n");
                }
            }
            System.out.println(" JSON converted to CSV: " + CSV_FILE);
        } catch (IOException e) {
            System.out.println("Error processing JSON file: " + e.getMessage());
        }
    }

    // Convert CSV to JSON
    public static void csvToJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String headerLine = reader.readLine();
            if (headerLine == null) {
                System.out.println(" Empty CSV file.");
                return;
            }

            String[] headers = headerLine.split(",");
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> student = new LinkedHashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    student.put(headers[i], values[i]);
                }
                students.add(student);
            }

            // Write JSON Output
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(OUTPUT_JSON_FILE), students);
            System.out.println(" CSV converted back to JSON: " + OUTPUT_JSON_FILE);
        } catch (IOException e) {
            System.out.println("Error processing CSV file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        jsonToCsv();
        csvToJson();
    }
}
