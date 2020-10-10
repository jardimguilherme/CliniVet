package model.entity;

public class Species {
    long id;
    String name;

    public Species() {
    }

    public Species(long id) {
        this.id = id;
    }

    public Species(String name) {
        this.name = name;
    }

    public Species(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
