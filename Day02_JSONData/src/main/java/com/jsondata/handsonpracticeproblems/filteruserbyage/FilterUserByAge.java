package com.jsondata.handsonpracticeproblems.filteruserbyage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class FilterUserByAge {
    public static void main(String[] args) {
        try {
            String filePath = "src/main/java/com/jsondata/handsonpracticeproblems/filteruserbyage/file.json";

            ObjectMapper objectMapper = new ObjectMapper();
            List<User> users = objectMapper.readValue(new File(filePath), new TypeReference<List<User>>() {});

            List<User> filteredUsers = users.stream()
                    .filter(user -> user.getAge() > 25)
                    .collect(Collectors.toList());

            filteredUsers.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
