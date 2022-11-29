package org.example.repositories.dao;

import org.example.entities.order.Order;
import org.example.entities.order.OrderStatus;
import org.example.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

abstract public class AbstractOrderService extends AbstractCRUD<Order>{


    protected AbstractOrderService(AbstractConnectionManager connectionManager) {
        super(connectionManager);
    }

    public List<Order> findByRole (OrderStatus status) throws SQLException{
        Connection dbConnection = null;
        List<Order> orders;
        try{
            dbConnection = connectionManager.getConnection();
            orders = findByTypeInternal(status, dbConnection);

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        finally {
            connectionManager.closeConnection(dbConnection);
        }
        return orders;
    };

    abstract protected List<Order> findByTypeInternal (OrderStatus status, Connection dbConnection) throws SQLException;
}
