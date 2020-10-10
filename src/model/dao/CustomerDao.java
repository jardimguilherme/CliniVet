package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.entity.Customer;

public class CustomerDao implements Dao<Customer> {
    Connection connection;
    AddressDao addressDao = new AddressDao();

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private final String table = "customer";

    @Override
    public Customer get(long id) {
        String sql = "SELECT * FROM " + table + " WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next())
                return createCustomerByResult(result);
        } catch (SQLException e) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no Customer.get().", e);
        }

        return null;
    }

    @Override
    public List<Customer> getAll() {
        String sql = "SELECT * FROM " + table + ";";
        List<Customer> customerList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next())
                customerList.add(createCustomerByResult(result));
            return customerList;
        } catch (SQLException e) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no CustomerDao.getAll().", e);
        }

        return null;
    }

    @Override
    public void save(Customer customer) {
        String sql = String.format("INSERT INTO %s(cpf, name, email, phone, mobile, address_id) VALUES(?, ?, ?, ?, ?, ?);", table);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getCpf());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setString(5, customer.getMobile());
            statement.setLong(6, customer.getAddress().getId());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, "Problema ocorrido no CustomerDao.save().", e);
        }
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE " + table + " SET "
                + " cpf = ?, "
                + " name = ?, "
                + " email = ?, "
                + " phone = ?, "
                + " mobile = ?, "
                + " WHERE id = ?;";


        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getCpf());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setString(5, customer.getMobile());
            statement.setLong(6, customer.getId());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no Customer.update().", e);
        }
    }

    @Override
    public void delete(Customer customer) {
        String sql = "DELETE FROM  " + table + " WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, customer.getId());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no Customer.delete().", e);
        }
    }

    private Customer createCustomerByResult(ResultSet result) throws SQLException {
        Customer customer = new Customer();
        customer.setId(result.getLong("id"));
        customer.setCpf(result.getString("cpf"));
        customer.setName(result.getString("name"));
        customer.setEmail(result.getString("email"));
        customer.setPhone(result.getString("phone"));
        customer.setMobile(result.getString("mobile"));
        //customer.setAddress(addressDao.get(result.getLong("address_id")));
        customer.setAddress(null);
        return customer;
    }
}

