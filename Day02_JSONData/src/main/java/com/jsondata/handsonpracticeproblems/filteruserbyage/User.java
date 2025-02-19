package com.jsondata.handsonpracticeproblems.filteruserbyage;

public class User {
    private String name;
    private int age;
    private String email;

    public User() {}

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return "Name: " + name + " age: " + age + " email: " + email;
    }
}
