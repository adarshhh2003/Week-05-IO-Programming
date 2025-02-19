package com.jsondata.handsonpracticeproblems.validatejsonemail;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ValidateJSONEmail {
    public static void main(String[] args) {
        try {
            String schemaJson = "src/main/java/com/jsondata/handsonpracticeproblems/validatejsonemail/schema.json";
            String dataJson = "src/main/java/com/jsondata/handsonpracticeproblems/validatejsonemail/data.json";

            String schemaContent = new String(Files.readAllBytes(Paths.get(schemaJson)));
            JSONObject jsonSchema = new JSONObject(new JSONTokener(schemaContent));

            String dataContent = new String(Files.readAllBytes(Paths.get(dataJson)));
            JSONObject jsonData = new JSONObject(new JSONTokener(dataContent));

            Schema schema = SchemaLoader.load(jsonSchema);

            schema.validate(jsonData);
            System.out.println("JSON is Valid!");
        } catch (Exception e) {
            System.out.println("JSON validation failed: " + e.getMessage());
        }
    }
}
