package com.jsondata.practiceproblems.cartojson;

import org.json.JSONObject;

class Car {
    private String brand;
    private String model;
    private double price;
    private int year;

    public Car(String brand, String model, double price, int year) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }
}

public class CarToJson {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Innova", 2000000.0, 2024);

        JSONObject carJson = new JSONObject();

        carJson.put("brand", car.getBrand());
        carJson.put("model", car.getModel());
        carJson.put("price", car.getPrice());
        carJson.put("year", car.getYear());

        System.out.println(carJson.toString(4));
    }
}
