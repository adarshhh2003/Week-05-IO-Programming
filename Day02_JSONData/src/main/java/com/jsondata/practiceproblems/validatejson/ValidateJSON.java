package com.jsondata.practiceproblems.validatejson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ValidateJSON {
    public static void main(String[] args) {
        try {
            String filePath = "src/main/java/com/jsondata/practiceproblems/validatejson/file.json";

            ObjectMapper objectMapper = new ObjectMapper();

            User user = objectMapper.readValue(new File(filePath), User.class);

            System.out.println("Valid JSON Structure");
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
        } catch (IOException e) {
            System.out.println("Invalid JSON Structure");
            e.printStackTrace();
        }
    }
}
