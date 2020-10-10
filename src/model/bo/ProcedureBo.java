package model.bo;

import model.dao.ProcedureDao;
import model.db.DbConnection;
import model.entity.Procedure;

import java.sql.Connection;
import java.util.List;

public class ProcedureBo {

    ProcedureDao procedureDao = new ProcedureDao();

    public ProcedureBo() {
        Connection connection = DbConnection.getInstance();
        procedureDao.setConnection(connection);
    }

    public List<Procedure> getAllProcedures(){
        return procedureDao.getAll();
    }
}
