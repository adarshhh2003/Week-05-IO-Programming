package com.csvproblems.basicproblems.writetocsvfile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToCSV {
    public static void main(String[] args) {
        String filePath = "src/main/java/com/csvproblems/basicproblems/writetocsvfile/employee.csv";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            writer.write("Id,Name,Department,Salary\n");
            writer.write("101,Uday,IT,28000\n");
            writer.write("102,Sanket,IT,28000\n");
            writer.write("103,Adarsh,IT,28000\n");
            writer.write("104,Harsh,HR,25000\n");
            writer.write("105,Arpit,GVT,50000\n");

            System.out.println("Employee details written to the CSV file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
