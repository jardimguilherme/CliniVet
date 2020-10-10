package model.dao;

import model.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentDao implements Dao<Payment>{
    Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private final String table = "payments";

    @Override
    public Payment get(long id) {
        String sql = "SELECT * FROM " + table + " WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Long.toString(id));
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createPaymentByResult(result);
            }
        } catch (SQLException e) {
            Logger.getLogger(PaymentDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no PaymentDao.get().", e);
        }

        return null;
    }

    @Override
    public List<Payment> getAll() {
        String sql = "SELECT * FROM " + table + ";";

        List<Payment> paymentList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                paymentList.add(createPaymentByResult(result));
            }
            return paymentList;
        } catch (SQLException e) {
            Logger.getLogger(PaymentDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no PaymentDao.get().", e);
        }

        return null;
    }

    @Override
    public void save(Payment payment) {
        String sql = "INSERT INTO " + table
                + "(type) VALUES(?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, payment.getType());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(PaymentDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no PaymentDao.get().", e);
        }
    }

    @Override
    public void update(Payment payment) {
        String sql = "UPDATE " + table + " SET "
                + "type = ?, "
                + "WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, payment.getType());
            statement.setLong(2, payment.getId());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(PaymentDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no PaymentDao.get().", e);
        }
    }

    @Override
    public void delete(Payment payment) {
        String sql = "DELETE FROM  " + table + " WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Long.toString(payment.getId()));
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(PaymentDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no PaymentDao.get().", e);
        }
    }

    private Payment createPaymentByResult(ResultSet result) throws SQLException {
        Payment payment = new Payment();
        payment.setId(result.getLong("id"));
        payment.setType(result.getString("type"));
        return payment;
    }
}
