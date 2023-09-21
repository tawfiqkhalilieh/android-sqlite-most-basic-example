package com.example.sqlite_sample;

public class User {
    private String id;
    private String username;
    private int age;
    private String password;

    public User (String id, String username, int age, String password) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.password = password;
    }

    private String getID() {
        return this.id;
    }

    private String getUsername() {
        return this.username;
    }

    private int getAge() {
        return this.age;
    }

    private String getPassword() {
        return this.password;
    }

    public String toString() {

        return String.format(
                    "id: {0}, username: {1}, age: {2}, password: {3}",
                    this.id, this.username, this.age, this.password
                );
    }
}
