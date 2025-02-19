package com.jsondata.practiceproblems.createobject;

import org.json.JSONArray;
import org.json.JSONObject;

public class CreateObject {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        JSONArray subjects = new JSONArray();
        subjects.put("Math");
        subjects.put("Physics");
        subjects.put("Computer Science");

        jsonObject.put("name", "Uday");
        jsonObject.put("age", 22);
        jsonObject.put("subjects", subjects);

        System.out.println(jsonObject.toString(3));
    }
}
