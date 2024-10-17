package util;

import exception.DBConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static Connection getConnection() throws DBConnectionException{
        try{
            String url = "jdbc:mysql://localhost:3306/CarConnect";
            String username = "root";
            String password = "Amit@123";
            return DriverManager.getConnection(url,username,password);
        } catch (SQLException e){
            throw new DBConnectionException("Failed to connect to database");
        }
    }
}
