package model.dao;

import model.entity.ProcedureSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcedureScheduleDao implements Dao<ProcedureSchedule> {
    Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private final String table = "procedure_schedule";

    AnimalDao animalDao = new AnimalDao();
    ProcedureDao procedureDao = new ProcedureDao();
    OrderDao orderDao = new OrderDao();

    @Override
    public ProcedureSchedule get(long id) {
        String sql = "SELECT * FROM " + table + " WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createProcedureScheduleByResult(result);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProcedureScheduleDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no ProcedureScheduleDao.get().", e);
        }

        return null;
    }

    @Override
    public List<ProcedureSchedule> getAll() {
        String sql = "SELECT * FROM " + table + ";";

        List<ProcedureSchedule> procedureScheduleList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                procedureScheduleList.add(createProcedureScheduleByResult(result));
            }
            return procedureScheduleList;
        } catch (SQLException e) {
            Logger.getLogger(ProcedureScheduleDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no ProcedureScheduleDao.getAll().", e);
        }

        return null;

    }

    @Override
    public void save(ProcedureSchedule procedureSchedule) {
        String sql = "INSERT INTO " + table
                + "(time, performed, procedure, animal) VALUES(?, ?, ?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setTime(1, procedureSchedule.getTime());
            statement.setBoolean(2, procedureSchedule.isPerformed());
            statement.setObject(3, procedureSchedule.getProcedure());
            statement.setObject(4, procedureSchedule.getAnimal());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(ProcedureScheduleDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no ProcedureScheduleDao.save().", e);
        }
    }

    @Override
    public void update(ProcedureSchedule procedureSchedule) {
        String sql = "UPDATE " + table + " SET "
                + "time = ?, "
                + "performed = ?, "
                + "procedure = ?, "
                + "animal = ?, "
                + "WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, procedureSchedule.getTime());
            statement.setBoolean(2, procedureSchedule.isPerformed());
            statement.setObject(3, procedureSchedule.getProcedure());
            statement.setObject(4, procedureSchedule.getAnimal());
            statement.setLong(5, procedureSchedule.getId());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(ProcedureScheduleDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no ProcedureScheduleDao.update().", e);
        }

        System.out.println(sql);
    }

    @Override
    public void delete(ProcedureSchedule procedureSchedule) {
        String sql = "DELETE FROM  " + table + " WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Long.toString(procedureSchedule.getId()));
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(ProcedureScheduleDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no ProcedureScheduleDao.delete().", e);
        }
    }

    private ProcedureSchedule createProcedureScheduleByResult(ResultSet result) throws SQLException {
        ProcedureSchedule procedureSchedule = new ProcedureSchedule();
        procedureSchedule.setId(result.getLong("id"));
//      procedureSchedule.setDate(result.getObject("date));
        procedureSchedule.setPerformed(result.getBoolean("performed"));
        procedureSchedule.setAnimal(animalDao.get(result.getLong("animal_id")));
        procedureSchedule.setProcedure(procedureDao.get(result.getLong("procedure_id")));
        procedureSchedule.setOrder(orderDao.get(result.getLong("order_id")));
        //procedureSchedule.
        return procedureSchedule;
    }
}
