package com.csvproblems.basicproblems.readcountrowsincsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadAndCount {
    public static void main(String[] args) {
        String filePath = "src/main/java/com/csvproblems/basicproblems/readcountrowsincsv/employees.csv";

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowCount = -1;

            while((line=reader.readLine())!=null) {
                rowCount++;
            }

            System.out.println("The number of rows in the CSV file is: " + rowCount );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
