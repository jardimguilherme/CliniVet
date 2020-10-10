package model.entity;

import java.util.Calendar;

public class Animal {
    double id;
    String name;
    double weight;
    String gender;
    String color;
    Calendar birthDate;
    Breed breed;
    Customer customer;


    public Animal() {
    }

    public Animal(double id) {
        this.id = id;
    }

    public Animal(String name) {
        this.name = name;
    }

    public Animal(double id, String name, double weight, String gender, String color, Calendar birthDate, Breed breed, Customer customer) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.color = color;
        this.birthDate = birthDate;
        this.breed = breed;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
