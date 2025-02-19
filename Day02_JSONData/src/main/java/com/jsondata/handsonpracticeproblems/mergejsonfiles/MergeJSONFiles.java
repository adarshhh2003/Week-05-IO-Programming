package com.jsondata.handsonpracticeproblems.mergejsonfiles;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;

public class MergeJSONFiles {
    public static void main(String[] args) {
        try {
            String file1 = "src/main/java/com/jsondata/handsonpracticeproblems/mergejsonfiles/file1.json";
            String file2 = "src/main/java/com/jsondata/handsonpracticeproblems/mergejsonfiles/file2.json";
            String mergedJson = "src/main/java/com/jsondata/handsonpracticeproblems/mergejsonfiles/mergedjson.json";

            JSONObject json1 = new JSONObject(new JSONTokener(new FileReader(file1)));

            JSONObject json2 = new JSONObject(new JSONTokener(new FileReader(file2)));

            for(String key : json2.keySet()) {
                json1.put(key, json2.get(key));
            }

            try(FileWriter file = new FileWriter(mergedJson)) {
                file.write(json1.toString(4));
            }

            System.out.println("JSON file merged successfully. Please check merged file");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
