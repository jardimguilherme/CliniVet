package test;

import model.dao.AddressDao;
import model.dao.SpeciesDao;
import model.db.DbConnection;
import model.entity.Species;

import java.sql.Connection;

public class SpeciesDaoTest {
    public static void main(String[] args) {
        Connection connection = DbConnection.getInstance();
        SpeciesDao dao = new SpeciesDao();
        dao.setConnection(connection);

        Species species = dao.get(1);
        System.out.println(species.getId());
       System.out.println(species.getName());
//        System.out.println(address.getCity());
//        System.out.println(address.getState());
    }
}
