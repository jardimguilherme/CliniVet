package model.dao;

import model.entity.Address;
import model.entity.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentDao implements Dao<Appointment> {
    Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private final String table = "appointments";

    @Override
    public Appointment get(long id) {
        String sql = "SELECT * FROM " + table + " WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return creatAppointmentByResult(result);
            }
        } catch (SQLException e) {
            Logger.getLogger(AppointmentDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AppointmentDao.get().", e);
        }

        return null;
    }

    @Override
    public List<Appointment> getAll() {
        String sql = "SELECT * FROM " + table + ";";

        List<Appointment> appointmentList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                appointmentList.add(creatAppointmentByResult(result));
            }
            return appointmentList;
        } catch (SQLException e) {
            Logger.getLogger(AppointmentDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AppointmentDao.getAll().", e);
        }

        return null;
    }

    @Override
    public void save(Appointment appointment) {
        String sql = "INSERT INTO " + table
                + "(type, price) VALUES(?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, appointment.getType());
            statement.setDouble(2, appointment.getPrice());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(AppointmentDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AppointmentDao.save().", e);
        }
    }

    @Override
    public void update(Appointment appointment) {
        String sql = "UPDATE " + table + " SET "
                + "type = ?,"
                + "price = ?, "
                + "WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, appointment.getType());
            statement.setDouble(2, appointment.getPrice());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(AppointmentDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AppointmentDao.update().", e);
        }

        System.out.println(sql);
    }

    @Override
    public void delete(Appointment appointment) {
        String sql = "DELETE FROM  " + table + " WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Long.toString(appointment.getId()));
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(AppointmentDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AppointmentDao.delete().", e);
        }
    }

    private Appointment creatAppointmentByResult(ResultSet result) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setId(result.getLong("id"));
        appointment.setType(result.getString("type"));
        appointment.setPrice(result.getDouble("price"));
        return appointment;
    }
}
