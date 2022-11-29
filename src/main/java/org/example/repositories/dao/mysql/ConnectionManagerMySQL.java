package org.example.repositories.dao.mysql;

import org.example.repositories.dao.AbstractConnectionManager;

import java.sql.*;

import static org.example.repositories.dao.mysql.ConfigsMySQL.*;

public class ConnectionManagerMySQL extends AbstractConnectionManager {
    @Override
    public Connection getConnection(){
        Connection dbConnection = null;
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not loaded");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection not created");
            e.printStackTrace();
        }
        return dbConnection;
    }
}
