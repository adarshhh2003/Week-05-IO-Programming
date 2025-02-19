package com.jsondata.practiceproblems.mergejsonobjects;

import org.json.JSONObject;

public class MergeJSONObjects {
    public static void main(String[] args) {
        JSONObject object1 = new JSONObject();
        object1.put("name", "uday");
        object1.put("age", 24);

        JSONObject object2 = new JSONObject();
        object2.put("email", "udaypratap.gmail.com");
        object2.put("address", "imaliya");

        for(String key : object2.keySet()) {
            object1.put(key, object2.get(key));
        }

        System.out.println(object1.toString(4));
    }
}
