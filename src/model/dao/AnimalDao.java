package model.dao;

import model.entity.Animal;
import model.entity.Customer;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnimalDao implements Dao<Animal>{
    Connection connection;
    BreedDao breedDao = new BreedDao();
    CustomerDao customerDao = new CustomerDao();

    private final String table = "animals";

    @Override
    public Animal get(long id) {
        String sql = "SELECT * FROM " + table + " WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next())
                return createAnimalByResult(result);
        }
        catch (SQLException e) {
            Logger.getLogger(AnimalDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AnimalDao.get().", e);
        }

        return null;
    }

    @Override
    public List<Animal> getAll() {
        String sql = "SELECT * FROM " + table + ";";
        List<Animal> animalList= new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next())
                animalList.add(createAnimalByResult(result));
            return animalList;
        }
        catch (SQLException e) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AnimalDao.getAll().", e);
        }
        return null;
    }

    @Override
    public void save(Animal animal) {

    }

    @Override
    public void update(Animal animal) {

    }

    @Override
    public void delete(Animal animal) {

    }

    private Animal createAnimalByResult(ResultSet result) throws SQLException {
        Animal animal = new Animal();
        animal.setId(result.getLong("id"));
        animal.setName(result.getString("name"));
        animal.setGender(result.getString("gender"));
        animal.setWeight(result.getDouble("weight"));
        //animal.setBirthDate(speciesDao.get(result.getLong("species_id")));
        animal.setColor(result.getString("color"));
        animal.setBreed(breedDao.get(result.getLong("breed_id")));
        animal.setCustomer(customerDao.get(result.getLong("customer_id")));
        return animal;
    }
}
