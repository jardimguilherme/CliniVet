package test;
import model.dao.AppointmentDao;
import model.db.DbConnection;
import model.entity.Appointment;

import java.sql.Connection;

public class AppointmentDaoTest {
    public static void main(String[]args){
        Connection connection  = DbConnection.getInstance();
        AppointmentDao dao = new AppointmentDao();
        dao.setConnection(connection);

        Appointment appointment = dao.get(2);

        System.out.println(appointment.getType());
        System.out.println(appointment.getPrice());
    }
}
