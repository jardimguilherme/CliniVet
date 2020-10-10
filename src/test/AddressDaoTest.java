package test;

import model.dao.AddressDao;
import model.db.DbConnection;
import model.entity.Address;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoTest {
    public static void main(String[] args) {
        Connection connection  = DbConnection.getInstance();
        AddressDao dao = new AddressDao();
        dao.setConnection(connection);

//        Address address = dao.get(1);
//
//        System.out.println(address.getId());
//        System.out.println(address.getStreet());
//        System.out.println(address.getCity());
//        System.out.println(address.getState());

//        List<Address> addressList = dao.getAll();
//
//        for(Address address : addressList){
//            System.out.println(address.getId());
//            System.out.println(address.getStreet());
//            System.out.println(address.getCity());
//            System.out.println(address.getState());
//        }

//        Address address = new Address();
//        address.setStreet("Rua da Paz");
//        address.setNumber(999);
//       // address.setComplement("Apto 1804");
//        address.setZipcode("12345678");
//        address.setCity("Campo Grande");
//        address.setState("MS");
//
//        dao.save(address);

//        Address address = dao.get(4);
//
//        address.setStreet("Rua Amazonas");
//
//        dao.update(address);

        Address address = dao.get(4);
        dao.delete(address);


    }
}
