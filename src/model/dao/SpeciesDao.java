package model.dao;

import model.entity.Species;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpeciesDao implements Dao<Species> {
    Connection connection;

    public void setConnection(Connection connection) {this.connection = connection;}

    private final String table = "species";


    @Override
    public Species get(long id) {
        String sql = "SELECT*FROM " + table + " WHERE id = ?;";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Long.toString(id));
            ResultSet result = statement.executeQuery();
            if(result.next())
                return createSpeciesByResult(result);
        }
        catch (SQLException e){
            Logger.getLogger(SpeciesDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no SpeciesDao.get().", e);
        }
        return null;
    }

    @Override
    public List<Species> getAll() {
        String sql = "SELECT * FROM " + table + ";";

        List<Species> speciesList= new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while(result.next())
                speciesList.add(createSpeciesByResult(result));
            return speciesList;
        }
        catch (SQLException e){
            Logger.getLogger(SpeciesDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no SpeciesDao.getAll().", e);
        }
        return null;
    }

    @Override
    public void save(Species species) {
        String sql = "INSERT INTO " + table
                + "(name) VALUES (?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, species.getName());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(SpeciesDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no SpeciesDao.save().", e);
        }
    }

    @Override
    public void update(Species species) {
        String sql = "UPDATE " + table + " SET name = ? WHERE id= ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, species.getName());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(SpeciesDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no SpeciesDao.update().", e);
        }

        System.out.println(sql);
    }

    @Override
    public void delete(Species species) {
        String sql = "DELETE FROM  " + table + " WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, species.getId());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(SpeciesDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no Species.delete().", e);
        }
    }

    private Species createSpeciesByResult(ResultSet result) throws SQLException {
        Species species = new Species();
        species.setId(result.getInt("id"));
        species.setName(result.getString("name"));
        return species;
    }
}
