package com.csvproblems.advancedproblems.databasetocsv;

import java.io.*;
import java.sql.*;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class DatabaseToCSV {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:h2:mem:testdb"; // In-Memory Database
        String dbUser = "sa";
        String dbPassword = "";
        String csvFilePath = "src/main/java/com/csvproblems/advancedproblems/databasetocsv/file.csv";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
             Statement statement = connection.createStatement()) {

            // Step 1: Create Table and Insert Sample Data
            String createTableSQL = "CREATE TABLE employees (EmployeeID INT, Name VARCHAR(50), Department VARCHAR(50), Salary DECIMAL(10,2))";
            statement.execute(createTableSQL);

            String insertDataSQL = "INSERT INTO employees VALUES " +
                    "(101, 'John Doe', 'IT', 60000), " +
                    "(102, 'Jane Smith', 'HR', 55000), " +
                    "(103, 'Michael Brown', 'Finance', 70000), " +
                    "(104, 'Emily Johnson', 'IT', 75000), " +
                    "(105, 'David Wilson', 'Marketing', 50000)";
            statement.execute(insertDataSQL);

            // Step 2: Fetch Data
            String sqlQuery = "SELECT EmployeeID, Name, Department, Salary FROM employees";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Step 3: Write Data to CSV
            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {
                writer.writeNext(new String[]{"Employee ID", "Name", "Department", "Salary"}); // Write Header

                while (resultSet.next()) {
                    String[] dataRow = {
                            resultSet.getString("EmployeeID"),
                            resultSet.getString("Name"),
                            resultSet.getString("Department"),
                            resultSet.getString("Salary")
                    };
                    writer.writeNext(dataRow);
                }
            }

            System.out.println("CSV Report generated successfully: " + csvFilePath);

        } catch (SQLException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
