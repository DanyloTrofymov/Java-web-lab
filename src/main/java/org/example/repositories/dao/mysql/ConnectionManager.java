package org.example.repositories.dao.mysql;

import java.sql.*;

import static org.example.repositories.dao.mysql.Configs.*;

public class ConnectionManager {
    public static Connection getConnection(){
        Connection dbConnection = null;
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        try {
            Class.forName("com.mysql.jdbc.Driver");
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
    public static void closeConnection(Connection conn){

    }
    public static void closeStatement(PreparedStatement statement){
        if (statement != null)
            try{
                statement.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
    }
}
