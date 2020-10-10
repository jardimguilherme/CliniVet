package model.entity;

import java.util.Calendar;

public class AppointmentSchedule {
    long id;
    Calendar time;
    String symptoms;
    boolean performed;
    Appointment appointment;
    Animal animal;
    Vet vet;
    Order order;

    public AppointmentSchedule() {
    }

    public AppointmentSchedule(long id) {
        this.id = id;
    }

    public AppointmentSchedule(Calendar time) {
        this.time = time;
    }

    public AppointmentSchedule(long id, Calendar time, String symptoms, boolean performed, Appointment appointment, Animal animal, Vet vet) {
        this.id = id;
        this.time = time;
        this.symptoms = symptoms;
        this.performed = performed;
        this.appointment = appointment;
        this.animal = animal;
        this.vet = vet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public boolean isPerformed() {
        return performed;
    }

    public void setPerformed(boolean performed) {
        this.performed = performed;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
