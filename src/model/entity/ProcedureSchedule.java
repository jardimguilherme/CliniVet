package model.entity;

import java.util.Calendar;

public class ProcedureSchedule {
    long id;
    Calendar time;
    boolean performed;
    Procedure procedure;
    Animal animal;
    Order order;


    public ProcedureSchedule() {
    }

    public ProcedureSchedule(long id) {
        this.id = id;
    }

    public ProcedureSchedule(Calendar time) {
        this.time = time;
    }

    public ProcedureSchedule(long id, Calendar time, boolean performed, Procedure procedure, Animal animal) {
        this.id = id;
        this.time = time;
        this.performed = performed;
        this.procedure = procedure;
        this.animal = animal;
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

    public boolean isPerformed() {
        return performed;
    }

    public void setPerformed(boolean performed) {
        this.performed = performed;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
