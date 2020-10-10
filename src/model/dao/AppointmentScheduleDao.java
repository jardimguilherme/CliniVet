package model.dao;

import model.entity.AppointmentSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentScheduleDao implements Dao<AppointmentSchedule> {
    Connection connection;

    AppointmentDao appointmentDao = new AppointmentDao();
    VetDao vetDao = new VetDao();
    AnimalDao animalDao = new AnimalDao();
    OrderDao orderDao = new OrderDao();

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private final String table = "appointment_schedule";

    @Override
    public AppointmentSchedule get(long id) {
        String sql = "SELECT * FROM " + table + " WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createAppointmentScheduleByResult(result);
            }
        } catch (SQLException e) {
            Logger.getLogger(AppointmentScheduleDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AppointmentScheduleDao.get().", e);
        }

        return null;
    }

    @Override
    public List<AppointmentSchedule> getAll() {
        String sql = "SELECT * FROM " + table + ";";

        List<AppointmentSchedule> appointmentScheduleList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                appointmentScheduleList.add(createAppointmentScheduleByResult(result));
            }
            return appointmentScheduleList;
        } catch (SQLException e) {
            Logger.getLogger(AppointmentScheduleDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AppointmentScheduleDao.getAll().", e);
        }

        return null;
    }

    @Override
    public void save(AppointmentSchedule appointmentSchedule) {
        String sql = "INSERT INTO " + table
                + "(time, symptoms, performed, appointment, animal, vet) VALUES(?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setTime(1, appointmentSchedule.getStreet());
            statement.setString(2, appointmentSchedule.getSymptoms());
            statement.setBoolean(3, appointmentSchedule.isPerformed());
            statement.setObject(4, appointmentSchedule.getAppointment());
            statement.setObject(5, appointmentSchedule.getAnimal());
            statement.setObject(6, appointmentSchedule.getVet());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(AppointmentScheduleDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AppointmentScheduleDao.save().", e);
        }
    }

    @Override
    public void update(AppointmentSchedule appointmentSchedule) {
        String sql = "UPDATE " + table + " SET "
                + "time = ?, "
                + "symptoms = ?, "
                + "performed = ?, "
                + "appointment = ?, "
                + "animal = ?, "
                + "vet = ? "
                + "WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, appointmentSchedule.getTime());
            statement.setString(2, appointmentSchedule.getSymptoms());
            statement.setBoolean(3, appointmentSchedule.isPerformed());
            statement.setObject(4, appointmentSchedule.getAppointment());
            statement.setObject(5, appointmentSchedule.getAnimal());
            statement.setObject(6, appointmentSchedule.getVet());
            statement.setLong(7, appointmentSchedule.getId());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(AppointmentScheduleDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AppointmentScheduleDao.update().", e);
        }

        System.out.println(sql);
    }

    @Override
    public void delete(AppointmentSchedule appointmentSchedule) {
        String sql = "DELETE FROM  " + table + " WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Long.toString(appointmentSchedule.getId()));
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(AppointmentScheduleDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AppointmentScheduleDao.delete().", e);
        }
    }

    private AppointmentSchedule createAppointmentScheduleByResult(ResultSet result) throws SQLException {
        AppointmentSchedule appointmentSchedule = new AppointmentSchedule();
        appointmentSchedule.setId(result.getLong("id"));
//        appointmentSchedule.set(result.getObject("time"));
        appointmentSchedule.setSymptoms(result.getString("symptoms"));
        appointmentSchedule.setPerformed(result.getBoolean("performed"));
        appointmentSchedule.setAppointment(appointmentDao.get(result.getLong("appointment_id")));
        appointmentSchedule.setAnimal(animalDao.get(result.getLong("animal")));
        appointmentSchedule.setVet(vetDao.get(result.getLong("employee_id")));
        appointmentSchedule.setOrder(orderDao.get(result.getLong("order_id")));
        return appointmentSchedule;
    }
}
