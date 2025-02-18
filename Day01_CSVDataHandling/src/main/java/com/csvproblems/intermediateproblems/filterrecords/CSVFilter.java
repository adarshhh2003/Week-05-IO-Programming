package com.csvproblems.intermediateproblems.filterrecords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVFilter {
    public static void filterRecords(String filePath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while((line=br.readLine())!=null) {
                if(isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(",");

                if(fields.length > 2) {
                    try {
                        int marks = Integer.parseInt(fields[1].trim());

                        if (marks > 80) {
                            System.out.println(line);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid marks format in row: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "src/main/java/com/csvproblems/intermediateproblems/filterrecords/employees.csv";
        filterRecords(filePath);
    }
}
