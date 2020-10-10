package test;

import model.dao.CustomerDao;
import model.db.DbConnection;
import model.entity.Customer;

import java.sql.Connection;
import java.util.List;

public class CustomerDaoTest {
    public static void main(String[] args) {
        Connection connection  = DbConnection.getInstance();
        CustomerDao dao = new CustomerDao();
        dao.setConnection(connection);

//        Customer customer = dao.get(1);
//        System.out.println(customer.getName());

        List<Customer> customerList = dao.getAll();

        for(Customer customer : customerList){
            System.out.println(customer.getName());
        }
    }
}
