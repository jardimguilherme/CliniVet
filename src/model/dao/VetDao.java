package model.dao;

import model.entity.Address;
import model.entity.Vet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VetDao implements Dao<Vet> {

    Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private final String table = "employees";

    @Override
    public Vet get(long id) {
        String sql = "SELECT * FROM " + table + " WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Long.toString(id));
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createVetByResult(result);
            }
        } catch (SQLException e) {
            Logger.getLogger(VetDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no VetDao.get().", e);
        }

        return null;


    }

    @Override
    public List<Vet> getAll() {
        String sql = "SELECT * FROM " + table + ";";

        List<Vet> vetList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                vetList.add(createVetByResult(result));
            }
            return vetList;
        } catch (SQLException e) {
            Logger.getLogger(VetDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no VetDao.getAll().", e);
        }

        return null;
    }

    @Override
    public void save(Vet vet) {
        String sql = "INSERT INTO " + table
                + "(crmv) VALUES(?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vet.getCrmv());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(VetDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no VetDao.save().", e);
        }
    }

    @Override
    public void update(Vet vet) {
        String sql = "UPDATE " + table + " SET "
                + "crmv = ?, "
                + "WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vet.getCrmv());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(VetDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no VetDao.update().", e);
        }

        System.out.println(sql);
    }

    @Override
    public void delete(Vet vet) {
        String sql = "DELETE FROM  " + table + " WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, vet.getId());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(VetDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no VetDao.delete().", e);
        }
    }

    private Vet createVetByResult(ResultSet result) throws SQLException {
        Vet vet = new Vet();
        vet.setId(result.getLong("id"));
        vet.setCrmv(result.getString("crmv"));
        return vet;
    }
}
