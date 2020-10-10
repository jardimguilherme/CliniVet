package model.dao;

import model.entity.Breed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BreedDao implements Dao<Breed> {
    Connection connection;
    SpeciesDao speciesDao = new SpeciesDao();

    public void setConnection(Connection connection) {this.connection = connection;}


    private final String table = "Breed";

    @Override
    public Breed get(long id) {
        String sql = "SELECT * FROM " + table + " WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next())
                return createBreedByResult(result);
        }
        catch (SQLException e) {
            Logger.getLogger(BreedDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no BreedDao.get().", e);
        }

        return null;
    }

    @Override
    public List<Breed> getAll() {
        String sql = "SELECT * FROM " + table + ";";

        List<Breed> breedList= new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next())
                breedList.add(createBreedByResult(result));
            return breedList;
        }
        catch (SQLException e){
            Logger.getLogger(BreedDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no BreedDao.getAll().", e);
        }
        return null;
    }

    @Override
    public void save(Breed breed) {
        String sql = "INSERT INTO " + table
                + "(name, species_id) VALUES (?,?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, breed.getName());
            statement.setLong(2, breed.getSpecies().getId());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(Breed.class.getName()).log(Level.SEVERE, "Problema ocorrido no BreedDao.save().", e);
        }
    }

    @Override
    public void update(Breed breed) {
        String sql = "UPDATE " + table + " SET name = ? WHERE id= ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, breed.getName());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(SpeciesDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no BreedDao.update().", e);
        }
    }

    @Override
    public void delete(Breed breed) {
        String sql = "DELETE FROM  " + table + " WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, breed.getId());
            statement.execute();
        }
        catch (SQLException e) {
            Logger.getLogger(BreedDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no Breed.delete().", e);
        }
    }

    private Breed createBreedByResult(ResultSet result) throws SQLException {
        Breed breed = new Breed();
        breed.setId(result.getLong("id"));
        breed.setName(result.getString("name"));
        breed.setSpecies(speciesDao.get(result.getLong("species_id")));
        return breed;
    }
}