package model.bo;

import model.dao.CustomerDao;
import model.db.DbConnection;
import model.entity.Customer;

import java.sql.Connection;
import java.util.List;

public class CustomerBo {

    CustomerDao customerDao = new CustomerDao();

    public CustomerBo() {
        Connection connection = DbConnection.getInstance();
        customerDao.setConnection(connection);
    }

    public List<Customer> getAllCustomers(){
        return customerDao.getAll();
    }
}
