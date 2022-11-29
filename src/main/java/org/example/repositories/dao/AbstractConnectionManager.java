package org.example.repositories.dao;

import org.example.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractConnectionManager {
    public abstract Connection getConnection();
    public void closeConnection(Connection dbConnection){
        if (dbConnection != null)
            try{
                dbConnection.close();
            } catch (SQLException e){
                throw new DatabaseException(e);
            }
    }
    public void closeStatement(PreparedStatement statement){
        if (statement != null)
            try{
                statement.close();
            } catch (SQLException e){
                throw new DatabaseException(e);
            }
    }
}
