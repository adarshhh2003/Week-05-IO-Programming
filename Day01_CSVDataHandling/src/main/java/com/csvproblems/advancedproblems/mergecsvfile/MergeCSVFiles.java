package com.csvproblems.advancedproblems.mergecsvfile;

import java.io.*;
import java.util.*;

public class MergeCSVFiles {
    public static void mergeCSVFiles(String file1, String file2, String outputFile) {
        Map<String, String[]> studentMap = new HashMap<>();


        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; // Skip header
                }
                String[] fields = line.split(",");
                if (fields.length < 3) continue;

                String id = fields[0].trim();
                studentMap.put(id, new String[]{fields[1].trim(), fields[2].trim(), "", ""});
            }
        } catch (IOException e) {
            System.out.println("Error reading file1: " + e.getMessage());
        }

        // Read students2.csv (ID, Marks, Grade)
        try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] fields = line.split(",");
                if (fields.length < 3) continue;

                String id = fields[0].trim();
                if (studentMap.containsKey(id)) {
                    studentMap.get(id)[2] = fields[1].trim(); // Marks
                    studentMap.get(id)[3] = fields[2].trim(); // Grade
                } else {
                    studentMap.put(id, new String[]{"", "", fields[1].trim(), fields[2].trim()});
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file2: " + e.getMessage());
        }


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("ID,Name,Age,Marks,Grade\n"); // Header
            for (Map.Entry<String, String[]> entry : studentMap.entrySet()) {
                String id = entry.getKey();
                String[] values = entry.getValue();
                bw.write(id + "," + String.join(",", values) + "\n");
            }
            System.out.println("\n Merged file created: " + outputFile);
        } catch (IOException e) {
            System.out.println("Error writing output file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String file1 = "src/main/java/com/csvproblems/advancedproblems/mergecsvfile/students1.csv";
        String file2 = "src/main/java/com/csvproblems/advancedproblems/mergecsvfile/students2.csv";
        String outputFile = "src/main/java/com/csvproblems/advancedproblems/mergecsvfile/newfile.csv";

        mergeCSVFiles(file1, file2, outputFile);
    }
}
