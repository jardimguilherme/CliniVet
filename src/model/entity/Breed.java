package model.entity;

public class Breed {
    long id;
    String name;
    Species species;

    public Breed() {
    }

    public Breed(long id) {
        this.id = id;
    }

    public Breed(String name) {
        this.name = name;
    }

    public Breed(long id, String name, Species species) {
        this.id = id;
        this.name = name;
        this.species = species;
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

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
}
