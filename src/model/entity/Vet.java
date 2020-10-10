package model.entity;

public class Vet extends Employee {

    long id;
    String crmv;

    public Vet() {
        super();
    }

    public Vet(long id) {
        super();
        this.id = id;
    }

    public Vet(String crmv) {
        super();
        this.crmv = crmv;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }
}
