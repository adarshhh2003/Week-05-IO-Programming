package com.csvproblems.advancedproblems.duplicatesincsvfile;

import java.io.*;
import java.util.*;

public class DuplicateInCSVFile {
    public static void findDuplicates(String filePath) {
        Map<String, String> recordMap = new HashMap<>();
        List<String> duplicates = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            String header = br.readLine();
            System.out.println(" Header: " + header);

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 0) continue;

                String id = fields[0].trim();

                if (recordMap.containsKey(id)) {
                    duplicates.add(line);
                } else {
                    recordMap.put(id, line);
                }
            }


            if (duplicates.isEmpty()) {
                System.out.println(" No duplicate records found.");
            } else {
                System.out.println(" Duplicate Records Found:");
                for (String record : duplicates) {
                    System.out.println(record);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filePath = "src/main/java/com/csvproblems/advancedproblems/duplicatesincsvfile/file.csv";
        findDuplicates(filePath);
    }
}
