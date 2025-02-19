package com.jsondata.practiceproblems.readjsonextractfields;

import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJSONExtractFields {
    public static void main(String[] args) {
        try {
            String filePath = "src/main/java/com/jsondata/practiceproblems/readjsonextractfields/file.json";
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            JSONObject jsonObject = new JSONObject(content);
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");

            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
