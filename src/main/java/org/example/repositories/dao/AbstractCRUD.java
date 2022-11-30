package org.example.repositories.dao;

import org.example.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractCRUD<T> implements CrudDao<T> {
    protected AbstractConnectionManager connectionManager;

    protected AbstractCRUD(AbstractConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void create(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Object must be not null");
        }
        Connection dbConnection = null;
        try {
            dbConnection = connectionManager.getConnection();
            createInternal(t, dbConnection);

        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            connectionManager.closeConnection(dbConnection);
        }
    }

    public List<T> findAll() {
        Connection dbConnection = null;
        List<T> list;
        try {
            dbConnection = connectionManager.getConnection();
            list = findAllInternal(dbConnection);

        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            connectionManager.closeConnection(dbConnection);
        }
        return list;
    }

    public T findById(String id) {
        Connection dbConnection = null;
        T object;
        try {
            dbConnection = connectionManager.getConnection();
            object = findByIdInternal(id, dbConnection);

        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            connectionManager.closeConnection(dbConnection);
        }
        return object;
    }

    public void update(String id, T t) {
        Connection dbConnection = null;
        try {
            dbConnection = connectionManager.getConnection();
            updateInternal(id, t, dbConnection);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            connectionManager.closeConnection(dbConnection);
        }
    }

    public void delete(String id) {
        Connection dbConnection = null;
        try {
            dbConnection = connectionManager.getConnection();
            deleteInternal(id, dbConnection);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            connectionManager.closeConnection(dbConnection);
        }
    }


    protected abstract void createInternal(T value, Connection connection) throws SQLException;

    protected abstract void deleteInternal(String id, Connection connection) throws SQLException;

    protected abstract void updateInternal(String id, T newValue, Connection connection) throws SQLException;

    protected abstract List<T> findAllInternal(Connection connection) throws SQLException;

    protected abstract T findByIdInternal(String id, Connection connection) throws SQLException;
}
