package model.dao;

import model.entity.Address;
import model.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDao implements Dao<Order> {
    Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private final String table = "orders";

    @Override
    public Order get(long id) {

        String sql = "SELECT * FROM " + table + " WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Long.toString(id));
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createOrderByResult(result);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no OrderDao.get().", e);
        }

        return null;

    }

    @Override
    public List<Order> getAll() {
        String sql = "SELECT * FROM " + table + ";";

        List<Order> orderList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                orderList.add(createOrderByResult(result));
            }
            return orderList;
        } catch (SQLException e) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no OrderDao.get().", e);
        }

        return null;


    }

    @Override
    public void save(Order order) {
//        String sql = "INSERT INTO " + table
//                + "(street, number, complement, zipcode, city, state, country) VALUES(?, ?, ?, ?, ?, ?, ?);";
//        try {
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, address.getStreet());
//            statement.setInt(2, address.getNumber());
//            statement.setString(3, address.getComplement());
//            statement.setString(4, address.getZipcode());
//            statement.setString(5, address.getCity());
//            statement.setString(6, address.getState());
//            statement.setString(7, address.getCountry());
//            statement.execute();
//        } catch (SQLException e) {
//            Logger.getLogger(AddressDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AddressDao.create().", e);
//        }
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(Order order) {

    }

    private Order createOrderByResult(ResultSet result) throws SQLException {
        Order order = new Order();
        order.setId(result.getLong("id"));
//        order.setTime(result.getObject("time"));
        order.setTotal(result.getDouble("total"));
//        order.setProduct(result.getObject("product"));
//        order.setPayment(result.getObject("payment"));
//        order.setAppointment(result.getObject("appointment"));
//        order.setProcedure(result.getObject("procedure"));
//        order.setCustomer(result.getObject("customer"));
        return order;
    }
}
