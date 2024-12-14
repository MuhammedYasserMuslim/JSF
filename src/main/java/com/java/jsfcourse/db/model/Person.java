package com.java.jsfcourse.db.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {

    private int id;
    private String username;
    private String password;
    private String name;

    public Person() {}
    public Person(String username, String password , String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }
    public Person(int id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("id: %d, username: %s, password: %s, name: %s", id, username, password, name);
    }
}
