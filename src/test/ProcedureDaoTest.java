package test;

import model.dao.ProcedureDao;
import model.dao.ProductDao;
import model.db.DbConnection;
import model.entity.Procedure;

import java.sql.Connection;
import java.util.List;

public class ProcedureDaoTest {

    public static void main(String[] args) {
        Connection connection = DbConnection.getInstance();
        ProcedureDao dao = new ProcedureDao();
        dao.setConnection(connection);

//        Procedure procedure = dao.get(2);
//
//        System.out.println(procedure.getName());
//        System.out.println(procedure.getPrice());

        List<Procedure> procedureList = dao.getAll();

        for (Procedure procedure : procedureList) {
            System.out.println(procedure.getName());
            System.out.println(procedure.getPrice());
        }
    }

}
