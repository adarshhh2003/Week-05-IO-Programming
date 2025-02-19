package com.jsondata.practiceproblems.listtojsonarray;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

class Car {
    private String brand;
    private String model;
    private int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }
}

public class ListToJSONArray {
    public static void main(String[] args) {
        try {

            List<Car> cars = Arrays.asList(
                    new Car("Toyota", "Innova", 2025),
                    new Car("Mahindra", "Scorpio", 2023),
                    new Car("Suzuki", "Baleno", 2024),
                    new Car("Land Rover", "Defender", 2025)
            );

            ObjectMapper objectMapper = new ObjectMapper();

            String jsonArray = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cars);

            System.out.println(jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
