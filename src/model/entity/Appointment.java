package model.entity;

public class Appointment {
    long id;
    String type;
    double price;

    public Appointment() {
    }

    public Appointment(long id) {
        this.id = id;
    }

    public Appointment(String type) {
        this.type = type;
    }

    public Appointment(long id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
