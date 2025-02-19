package com.jsondata.handsonpracticeproblems.jsontoxmlconverter;

import org.json.JSONObject;
import org.json.XML;

public class JSONToXmlConverter {
    public static void main(String[] args) {
        String jsonString = """ 
            {
            "name": "Alice",
            "age": 25,
            "email": "alice@example.com"
        }
        """;

        JSONObject jsonObject = new JSONObject(jsonString);
        String xmlString = XML.toString(jsonObject, "User");

        System.out.println("Converted XML: \n" + xmlString);
    }
}
