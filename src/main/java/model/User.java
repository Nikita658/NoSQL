package model;

import java.util.List;
import java.util.UUID;

public final class User {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String workplace;
    private String city;
    private List<String> accounts;

    public User(String id, String firstName, String lastName, int age, String workplace, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.workplace = workplace;
        this.city = city;
        this.id = UUID.randomUUID().toString();
    }

    public User(String firstName, String lastName, Integer age, String workplace, String city) {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}