package model.dao;

import model.entity.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressDao implements Dao<Address> {

    Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private final String table = "addresses";

    @Override
    public Address get(long id) {
        String sql = "SELECT * FROM " + table + " WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createAddressByResult(result);
            }
        } catch (SQLException e) {
            Logger.getLogger(AddressDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AddressDao.get().", e);
        }

        return null;
    }

    @Override
    public List<Address> getAll() {
        String sql = "SELECT * FROM " + table + ";";

        List<Address> addressList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                addressList.add(createAddressByResult(result));
            }
            return addressList;
        } catch (SQLException e) {
            Logger.getLogger(AddressDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AddressDao.getAll().", e);
        }

        return null;
    }

    @Override
    public void save(Address address) {
        String sql = "INSERT INTO " + table
                + "(street, number, complement, zipcode, city, state, country) VALUES(?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, address.getStreet());
            statement.setInt(2, address.getNumber());
            statement.setString(3, address.getComplement());
            statement.setString(4, address.getZipcode());
            statement.setString(5, address.getCity());
            statement.setString(6, address.getState());
            statement.setString(7, address.getCountry());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(AddressDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AddressDao.create().", e);
        }
    }

    @Override
    public void update(Address address) {
        String sql = "UPDATE " + table + " SET "
                + "street = ?, "
                + "number = ?, "
                + "complement = ?, "
                + "zipcode = ?, "
                + "city = ?, "
                + "state = ? "
                + "country = ? "
                + "WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, address.getStreet());
            statement.setInt(2, address.getNumber());
            statement.setString(3, address.getComplement());
            statement.setString(4, address.getZipcode());
            statement.setString(5, address.getCity());
            statement.setString(6, address.getState());
            statement.setString(7, address.getCountry());
            statement.setLong(8, address.getId());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(AddressDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AddressDao.create().", e);
        }

        System.out.println(sql);
    }

    @Override
    public void delete(Address address) {
        String sql = "DELETE FROM  " + table + " WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, address.getId());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(AddressDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AddressDao.get().", e);
        }
    }

    private Address createAddressByResult(ResultSet result) throws SQLException {
        Address address = new Address();
        address.setId(result.getLong("id"));
        address.setStreet(result.getString("street"));
        address.setNumber(result.getInt("number"));
        address.setComplement(result.getString("complement"));
        address.setZipcode(result.getString("zipcode"));
        address.setCity(result.getString("city"));
        address.setState(result.getString("state"));
        address.setCountry(result.getString("country"));
        return address;
    }
}
