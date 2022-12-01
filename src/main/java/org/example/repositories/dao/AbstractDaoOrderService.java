package org.example.repositories.dao;

import org.example.entities.order.Order;
import org.example.entities.order.OrderStatus;
import org.example.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

abstract public class AbstractDaoOrderService extends AbstractCRUD<Order>{

    protected AbstractDaoOrderService(AbstractConnectionManager connectionManager) {
        super(connectionManager);
    }

    public List<Order> findByStatus (OrderStatus status){
        Connection dbConnection = null;
        List<Order> orders;
        try{
            dbConnection = connectionManager.getConnection();
            orders = findByStatusInternal(status, dbConnection);

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        finally {
            connectionManager.closeConnection(dbConnection);
        }
        return orders;
    };

    abstract protected List<Order> findByStatusInternal (OrderStatus status, Connection dbConnection) throws SQLException;
}
