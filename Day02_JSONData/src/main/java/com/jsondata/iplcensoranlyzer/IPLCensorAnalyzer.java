package com.jsondata.iplcensoranlyzer;

import org.json.JSONArray;
import org.json.JSONObject;
import com.opencsv.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IPLCensorAnalyzer {

    public static void main(String[] args) {
        String inputJsonFile = "src/main/java/com/jsondata/iplcensoranlyzer/inputjsonfile.json";
        String inputCsvFile = "src/main/java/com/jsondata/iplcensoranlyzer/inputcsvfile.csv";
        String outputJsonFile = "src/main/java/com/jsondata/iplcensoranlyzer/outputjsonfile.json";
        String outputCsvFile = "src/main/java/com/jsondata/iplcensoranlyzer/outputcsvfile.csv";

        // Process JSON File
        processJsonFile(inputJsonFile, outputJsonFile);

        // Process CSV File
        processCsvFile(inputCsvFile, outputCsvFile);
    }

    // Process JSON Data
    private static void processJsonFile(String inputFile, String outputFile) {
        try {
            // Read JSON
            String content = new String(Files.readAllBytes(Paths.get(inputFile)));
            JSONArray jsonArray = new JSONArray(content);

            // Apply Censorship
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject match = jsonArray.getJSONObject(i);
                match.put("team1", censorTeamName(match.getString("team1")));
                match.put("team2", censorTeamName(match.getString("team2")));
                match.put("player_of_match", "REDACTED");
            }

            // Write Censored JSON
            Files.write(Paths.get(outputFile), jsonArray.toString(4).getBytes());
            System.out.println("Censored JSON saved as: " + outputFile);

        } catch (Exception e) {
            System.out.println("Error processing JSON: " + e.getMessage());
        }
    }

    // Process CSV Data
    private static void processCsvFile(String inputFile, String outputFile) {
        try (Reader reader = new FileReader(inputFile);
             CSVReader csvReader = new CSVReader(reader);
             FileWriter writer = new FileWriter(outputFile);
             CSVWriter csvWriter = new CSVWriter(writer)) {

            List<String[]> records = csvReader.readAll();
            if (records.isEmpty()) {
                throw new RuntimeException("CSV file is empty!");
            }

            // Process Header
            String[] headers = records.get(0);
            csvWriter.writeNext(headers);

            // Process Rows
            for (int i = 1; i < records.size(); i++) {
                String[] row = records.get(i);
                row[1] = censorTeamName(row[1]); // Censor team1
                row[2] = censorTeamName(row[2]); // Censor team2
                row[3] = "REDACTED"; // Censor Player of the Match
                csvWriter.writeNext(row);
            }

            System.out.println("Censored CSV saved as: " + outputFile);

        } catch (Exception e) {
            System.out.println("Error processing CSV: " + e.getMessage());
        }
    }

    // Function to Mask Team Name
    private static String censorTeamName(String teamName) {
        String[] words = teamName.split(" ");
        if (words.length > 1) {
            words[words.length - 1] = "***";
        }
        return String.join(" ", words);
    }
}
