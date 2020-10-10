package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static Connection connection;
    private static final String url = "jdbc:sqlite:db/database.sqlite";

    private static Connection createConnection() {
        Connection newConnection = null;
        try {
            newConnection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return newConnection;
    }

    public static synchronized Connection getInstance(){
        if(connection == null){
            connection = createConnection();
        }
        return connection;
    }

}
