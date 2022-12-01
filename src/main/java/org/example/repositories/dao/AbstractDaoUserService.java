package org.example.repositories.dao;

import org.example.entities.user.User;
import org.example.entities.user.UserRole;
import org.example.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

abstract public class AbstractUserService extends AbstractCRUD<User> {

    protected AbstractUserService(AbstractConnectionManager connectionManager) {
        super(connectionManager);
    }

    public List<User> findByRole (UserRole role){
        Connection dbConnection = null;
        List<User> users;
        try{
            dbConnection = connectionManager.getConnection();
            users = findByRoleInternal(role, dbConnection);

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        finally {
            connectionManager.closeConnection(dbConnection);
        }
        return users;
    };

    public User findByUsername (String username){
        Connection dbConnection = null;
        User user;
        try{
            dbConnection = connectionManager.getConnection();
            user = findByUsernameInternal(username, dbConnection);

        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            connectionManager.closeConnection(dbConnection);
        }
        return user;
    };

    abstract protected User findByUsernameInternal(String username, Connection dbConnection) throws SQLException;

    abstract protected List<User> findByRoleInternal (UserRole role, Connection dbConnection) throws SQLException;
}
