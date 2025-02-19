package com.jsondata.handsonpracticeproblems.csvtojsonconverter;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVToJSONConverter {
    public static void main(String[] args) {
        try {
            String data = "src/main/java/com/jsondata/handsonpracticeproblems/csvtojsonconverter/data.csv";
            List<String> lines = Files.readAllLines(Paths.get(data));

            if (lines.isEmpty()) throw new RuntimeException("CSV file is empty!");

            // Extract headers
            String[] headers = lines.get(0).split(",");

            // Create JSON Array
            JSONArray jsonArray = new JSONArray();

            // Convert each row into a JSON Object
            for (int i = 1; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                JSONObject jsonObject = new JSONObject();

                for (int j = 0; j < headers.length; j++) {
                    jsonObject.put(headers[j].trim(), values[j].trim());
                }

                jsonArray.put(jsonObject);
            }

            // Print JSON output
            System.out.println(jsonArray.toString(4));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
