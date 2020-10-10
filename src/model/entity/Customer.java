package model.entity;

public class Customer extends Person {

    long id;

    public Customer() {
    }

    public Customer(String cpf) {
        super(cpf);
    }

    public Customer(long id) {
        this.id = id;
    }

    public Customer(String cpf, long id) {
        super(cpf);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
