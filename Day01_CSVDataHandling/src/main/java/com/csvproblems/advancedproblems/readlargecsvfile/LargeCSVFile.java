package com.csvproblems.advancedproblems.readlargecsvfile;

import java.io.*;

public class LargeCSVFile {
    private static final int CHUNK_SIZE = 100; // Process 100 lines at a time

    public static void readCSVInChunks(String filePath) {
        int totalRecordsProcessed = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int recordCount = 0;


            String header = br.readLine();
            System.out.println(" Header: " + header);

            while ((line = br.readLine()) != null) {

                recordCount++;
                totalRecordsProcessed++;

                if (recordCount == CHUNK_SIZE) {
                    System.out.println(" Processed " + totalRecordsProcessed + " records so far...");
                    recordCount = 0;
                }
            }


            if (recordCount > 0) {
                System.out.println(" Processed " + totalRecordsProcessed + " records (final batch).");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filePath = "src/main/java/com/csvproblems/advancedproblems/readlargecsvfile/file.csv";
        readCSVInChunks(filePath);
    }
}
