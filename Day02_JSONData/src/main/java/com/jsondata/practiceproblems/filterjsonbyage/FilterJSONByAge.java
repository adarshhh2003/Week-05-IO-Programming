package com.jsondata.practiceproblems.filterjsonbyage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class FilterJSONByAge {
    public static void main(String[] args) {
        try {
            String filePath = "src/main/java/com/jsondata/practiceproblems/filterjsonbyage/file.json";

            ObjectMapper objectMapper = new ObjectMapper();

            List<Person> people = objectMapper.readValue(new File(filePath), new TypeReference<List<Person>>() {});

            List<Person> filteredPeople = people.stream()
                    .filter(person -> person.getAge() > 25)
                    .collect(Collectors.toList());

            filteredPeople.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
