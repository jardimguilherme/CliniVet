package model.dao;

import model.entity.Address;
import model.entity.Procedure;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcedureDao implements Dao<Procedure>{
    Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private final String table = "procedures";

    @Override
    public Procedure get(long id) {
        String sql = "SELECT * FROM " + table + " WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createProcedureByResult(result);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProcedureDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no ProcedureDao.get().", e);
        }

        return null;
    }

    @Override
    public List<Procedure> getAll() {
        String sql = "SELECT * FROM " + table + ";";

        List<Procedure> procedureList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                procedureList.add(createProcedureByResult(result));
            }
            return procedureList;
        } catch (SQLException e) {
            Logger.getLogger(ProcedureDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no ProcedureDao.getAll().", e);
        }

        return null;
    }

    @Override
    public void save(Procedure procedure) {
        String sql = "INSERT INTO " + table
                + "(name, price) VALUES(?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, procedure.getName());
            statement.setDouble(2, procedure.getPrice());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(ProcedureDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no ProcedureDao.save().", e);
        }
    }

    @Override
    public void update(Procedure procedure) {
        String sql = "UPDATE " + table + " SET "
                + "name = ?, "
                + "price = ?, "
                + "WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, procedure.getName());
            statement.setDouble(2, procedure.getPrice());
            statement.setLong(3, procedure.getId());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(ProcedureDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no ProcedureDao.update().", e);
        }
    }

    @Override
    public void delete(Procedure procedure) {
        String sql = "DELETE FROM  " + table + " WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, procedure.getId());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(ProcedureDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no ProcedureDao.delete().", e);
        }
    }

    private Procedure createProcedureByResult(ResultSet result) throws SQLException {
        Procedure procedure = new Procedure();
        procedure.setId(result.getLong("id"));
        procedure.setName(result.getString("name"));
        procedure.setPrice(result.getDouble("price"));
        return procedure;
    }
}
