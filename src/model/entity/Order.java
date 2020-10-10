package model.entity;

import java.util.Calendar;

public class Order {
    long id;
    Calendar time;
    double total;
    Product product;
    Payment payment;
    Appointment appointment;
    Procedure procedure;
    Customer customer;

    public Order() {
    }

    public Order(long id) {
        this.id = id;
    }

    public Order(Calendar time) {
        this.time = time;
    }

    public Order(long id, Calendar time, double total, Product product, Payment payment, Appointment appointment, Procedure procedure, Customer customer) {
        this.id = id;
        this.time = time;
        this.total = total;
        this.product = product;
        this.payment = payment;
        this.appointment = appointment;
        this.procedure = procedure;
        this.customer = customer;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
