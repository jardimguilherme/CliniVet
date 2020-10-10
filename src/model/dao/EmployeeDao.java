package model.dao;
import model.entity.Address;
import model.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDao implements Dao<Employee> {

    Connection connection;
    AddressDao addressDao = new AddressDao();

    public void setConnection(Connection connection) {
        this.connection = connection;
        addressDao.setConnection(connection);
    }

    private final String table = "employees";


    @Override
    public Employee get(long id) {
        String sql = "SELECT * FROM " + table + " WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createEmployeeByResult(result);
            }
        } catch (SQLException e) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no EmployeeDao.get().", e);
        }

        return null;
    }

    public Employee get(String login, String password){
        String sql = "SELECT * FROM " + table + " WHERE login = ? AND password = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createEmployeeByResult(result);
            }
        } catch (SQLException e) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no EmployeeDao.get().", e);
        }

        return null;
    }

    @Override
    public List<Employee> getAll() {
        String sql = "SELECT * FROM " + table + ";";

        List<Employee> employeeList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                employeeList.add(createEmployeeByResult(result));
            }
            return employeeList;
        } catch (SQLException e) {
            Logger.getLogger(AddressDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AddressDao.getAll().", e);
        }

        return null;
    }

    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO " + table
                + "(cpf, crmv, name, email, phone, mobile, address, register, login, password, birthDate, admission) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, employee.getCpf());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getPhone());
            statement.setString(5, employee.getMobile());
            statement.setObject(6, employee.getAddress());
            statement.setLong(7, employee.getRegister());
            statement.setString(8, employee.getLogin());
            statement.setString(9, employee.getPassword());
//            statement.setObject(10, employee.getBirthDate());
//            statement.setObject(11, employee.getAdmission());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(AddressDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no AddressDao.create().", e);
        }
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE " + table + " SET "
                + "cpf = ?, "
                + "name = ?, "
                + "email = ?, "
                + "phone = ?, "
                + "mobile = ?, "
                + "address = ? "
                + "register = ? "
                + "login = ? "
                + "password = ? "
                + "birthDate = ? "
                + "admission = ? "
                + "WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, employee.getCpf());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getPhone());
            statement.setString(5, employee.getMobile());
            statement.setObject(6, employee.getAddress());
            statement.setLong(7, employee.getRegister());
            statement.setString(8, employee.getLogin());
            statement.setString(9, employee.getPassword());
//            statement.setObject(10, employee.getBirthDate());
//            statement.setObject(11, employee.getAdmission());
            statement.setLong(12, employee.getId());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no EmployeeDao.create().", e);
        }

        System.out.println(sql);
    }

    @Override
    public void delete(Employee employee) {
        String sql = "DELETE FROM  " + table + " WHERE id = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Long.toString(employee.getId()));
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, "Problema ocorrido no EmployeeDao.get().", e);
        }
    }

    private Employee createEmployeeByResult(ResultSet result) throws SQLException {
        Employee employee = new Employee();
        employee.setId(result.getLong("id"));
        employee.setRegister(result.getLong("register"));
        employee.setCpf(result.getString("cpf"));
        employee.setCrmv(result.getString("crmv"));
        employee.setLogin((result.getString("login")));
        employee.setPassword(result.getString("password"));
        //employee.setAdmission();
        employee.setAdmin(result.getBoolean("admin"));
        employee.setName(result.getString("name"));
        employee.setEmail(result.getString("email"));
        //employee.setBirthDate();
        employee.setPhone(result.getString("phone"));
        employee.setMobile(result.getString("mobile"));
        employee.setAddress(addressDao.get(result.getLong("address_id")));
        return employee;
    }
}
