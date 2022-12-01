package org.example.repositories.dao;

import org.example.entities.good.Good;
import org.example.entities.good.GoodType;
import org.example.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

abstract public class AbstractDaoGoodService extends AbstractCRUD<Good>{

    protected AbstractDaoGoodService(AbstractConnectionManager connectionManager) {
        super(connectionManager);
    }

    public List<Good> findByType (GoodType type){
        Connection dbConnection = null;
        List<Good> goods;
        try{
            dbConnection = connectionManager.getConnection();
            goods = findByTypeInternal(type, dbConnection);

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        finally {
            connectionManager.closeConnection(dbConnection);
        }
        return goods;
    }

    public Good findByName (String name){
        Connection dbConnection = null;
        Good good;
        try{
            dbConnection = connectionManager.getConnection();
            good = findByNameInternal(name, dbConnection);

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        finally {
            connectionManager.closeConnection(dbConnection);
        }
        return good;
    }

    abstract protected Good findByNameInternal (String name, Connection dbConnection) throws SQLException;
    abstract protected List<Good> findByTypeInternal (GoodType type, Connection dbConnection) throws SQLException;
}
