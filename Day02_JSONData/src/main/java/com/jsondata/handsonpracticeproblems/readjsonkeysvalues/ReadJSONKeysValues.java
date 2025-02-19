package com.jsondata.handsonpracticeproblems.readjsonkeysvalues;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class ReadJSONKeysValues {
    public static void main(String[] args) {
        try {
            String filePath = "src/main/java/com/jsondata/handsonpracticeproblems/readjsonkeysvalues/file.json";

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            printJsonKeysValues(rootNode, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printJsonKeysValues(JsonNode node, String prefix) {
        if(node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();

            while(fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                printJsonKeysValues(entry.getValue(), prefix + entry.getKey() + ".");
            }
        } else if(node.isArray()) {

            for(int i=0; i< node.size(); i++) {
                printJsonKeysValues(node.get(i), prefix + "[" + i + "] ");
            }
        } else {
            System.out.println(prefix.substring(0, prefix.length()-1) + "->" + node.asText());
        }
    }
}
