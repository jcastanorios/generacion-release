package com.mercadolibre.spring.course.models;

public class User {

    private String name;
    private String lastName;

    public User(){
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }
}
