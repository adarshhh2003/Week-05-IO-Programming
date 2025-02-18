package com.csvproblems.intermediateproblems.searchrecordincsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SearchRecord {
    public static void findRecords(String filePath, String targetName) {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while((line=br.readLine())!=null) {
                if(isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] fields = line.split(",");

                if(fields.length > 0) {
                    try {
                        String name = fields[1].trim();

                        if (name.equalsIgnoreCase(targetName)) {
                            System.out.println(line);
                            return;
                        }
                    } catch (Exception e) {
                        System.err.println("Name is invalid: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String filePath = "src/main/java/com/csvproblems/basicproblems/writetocsvfile/employee.csv";
        System.out.println("Enter the Employee Name: ");
        String targetName = scanner.nextLine().trim();

        findRecords(filePath, targetName);
    }
}
