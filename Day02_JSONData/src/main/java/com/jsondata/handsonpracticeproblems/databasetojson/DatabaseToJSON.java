package com.jsondata.handsonpracticeproblems.databasetojson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class DatabaseToJSON {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb"; // H2 in-memory database (temporary)

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Create a temporary table
            stmt.execute("CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50), age INT, email VARCHAR(100))");

            // Insert sample data
            stmt.execute("INSERT INTO users VALUES (1, 'uday', 25, 'uday@example.com')");
            stmt.execute("INSERT INTO users VALUES (2, 'sanket', 30, 'sanket@example.com')");
            stmt.execute("INSERT INTO users VALUES (3, 'harsh', 28, 'harsh@example.com')");

            // Query data
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            // Convert ResultSet to JSON
            JSONArray jsonArray = new JSONArray();
            while (rs.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", rs.getInt("id"));
                jsonObject.put("name", rs.getString("name"));
                jsonObject.put("age", rs.getInt("age"));
                jsonObject.put("email", rs.getString("email"));
                jsonArray.put(jsonObject);
            }

            // Print JSON Report
            System.out.println(jsonArray.toString(4));

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}
