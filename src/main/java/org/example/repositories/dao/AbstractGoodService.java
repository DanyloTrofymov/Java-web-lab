package org.example.repositories.dao;

import org.example.entities.good.Good;
import org.example.entities.good.GoodType;
import org.example.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

abstract public class AbstractGoodService extends AbstractCRUD<Good>{

    protected AbstractGoodService(AbstractConnectionManager connectionManager) {
        super(connectionManager);
    }

    public List<Good> findByType (GoodType type){
        Connection dbConnection = null;
        List<Good> orders;
        try{
            dbConnection = connectionManager.getConnection();
            orders = findByTypeInternal(type, dbConnection);

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        finally {
            connectionManager.closeConnection(dbConnection);
        }
        return orders;
    };

    abstract protected List<Good> findByTypeInternal (GoodType type, Connection dbConnection) throws SQLException;
}
