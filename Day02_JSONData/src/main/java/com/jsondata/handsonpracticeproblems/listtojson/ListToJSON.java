package com.jsondata.handsonpracticeproblems.listtojson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class ListToJSON {
    public static void main(String[] args) {
        try {
            List<Person> people = Arrays.asList(
                    new Person("Uday", 25, "uday@email.com"),
                    new Person("Aman", 26, "aman@gmail.com"),
                    new Person("Sanjay", 27, "sanjay@example.com")
            );

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonArray = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(people);

            System.out.println(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
