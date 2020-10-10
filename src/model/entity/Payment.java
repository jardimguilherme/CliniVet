package model.entity;

public class Payment {
    long id;
    String type;

    public Payment() {
    }

    public Payment(long id) {
        this.id = id;
    }

    public Payment(String type) {
        this.type = type;
    }

    public Payment(long id, String type) {
        this.id = id;
        this.type = type;
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
}
