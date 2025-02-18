package com.csvproblems.basicproblems.readcsvfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
    public static void main(String[] args) {
        String filePath = "src/main/java/com/csvproblems/basicproblems/readcsvfile/students.csv";

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line=reader.readLine())!=null) {
                String[] values = line.split(",");
                System.out.println("Id: " + values[0] + " Name: " + values[1] + " Age: " + values[2] + " Marks: " + values[3]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
