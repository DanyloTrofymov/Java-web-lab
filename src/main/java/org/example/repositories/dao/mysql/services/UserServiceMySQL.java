package org.example.repositories.dao.mysql.services;

import org.example.entities.user.Role;
import org.example.entities.user.User;
import org.example.repositories.dao.AbstractDAO;
import org.example.repositories.dao.mysql.ConnectionManagerMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserServiceMySQL extends AbstractDAO<User> {
    public UserServiceMySQL(){
        connectionManager = new ConnectionManagerMySQL();
    }
    @Override
    protected void createInternal(User user, Connection dbConnection) throws SQLException {
        int roleId = getRoleId(user.getRole());
        String sql = """
            INSERT INTO `java_labs`.`user` (`username`, `password`, `firstname`, `lastname`, `role_id`) VALUES (?, ?, ?, ?, ?)
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getFirstname());
        preparedStatement.setString(4, user.getLastname());
        preparedStatement.setInt(5, roleId);
        preparedStatement.executeUpdate();

        connectionManager.closeStatement(preparedStatement);
    }

    @Override
    protected void deleteInternal(int id, Connection dbConnection) throws SQLException {
        String sql = """
            DELETE FROM `user`
            WHERE `id` = ?;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        connectionManager.closeStatement(preparedStatement);
    }

    @Override
    protected void updateInternal(int id, User newUser, Connection dbConnection) throws SQLException {
        int roleId = getRoleId(newUser.getRole());
        String sql = """
            UPDATE `user`
            SET
            `username` = ?,
            `password` = ?,
            `firstname` = ?,
            `lastname` = ?,
            `role_id` = ?
            WHERE `id` = ?;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setString(1, newUser.getUsername());
        preparedStatement.setString(2, newUser.getPassword());
        preparedStatement.setString(3, newUser.getFirstname());
        preparedStatement.setString(4, newUser.getLastname());
        preparedStatement.setInt(5, roleId);
        preparedStatement.setInt(6, id);
        preparedStatement.executeUpdate();
        connectionManager.closeStatement(preparedStatement);
    }

    @Override
    protected List<User> findAllInternal(Connection dbConnection) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = """
            SELECT `user`.`id`, `username`, `password`, `firstname`, `lastname`, `user_role`.`role` FROM `user`
            INNER JOIN `user_role`
            ON `user`.`role_id` = `user_role`.`id`;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        ResultSet result = preparedStatement.executeQuery();
        while(result.next()) {
            User user = setFields(result);
            users.add(user);
        }
        connectionManager.closeStatement(preparedStatement);
        return users;
    }

    @Override
    protected User findByIdInternal(int id, Connection dbConnection) throws SQLException {
        User user = new User();
        String sql = """
            SELECT `user`.`id`, `username`, `password`, `firstname`, `lastname`, `user_role`.`role` FROM `user`
            INNER JOIN `user_role`
            ON `user`.`role_id` = `user_role`.`id` WHERE `user`.`id` = ?;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            user = setFields(result);
        }
        connectionManager.closeStatement(preparedStatement);

        return user;
    }

    private User setFields(ResultSet result) throws SQLException {
        User user = new User();

        user.setId(result.getInt("id"));
        user.setFirstname(result.getString("firstname"));
        user.setLastname(result.getString("lastname"));
        user.setRole(result.getString("role"));
        user.setUsername(result.getString("username"));
        user.setPassword(result.getString("password"));

        return user;
    }

    private int getRoleId(Role role) throws SQLException {
        Connection dbConnection = connectionManager.getConnection();
        String selectRole = """
            SELECT `id` FROM `java_labs`.`user_role` WHERE `role` = ?
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(selectRole);
        preparedStatement.setString(1, role.toString().toLowerCase());
        ResultSet result = preparedStatement.executeQuery();

        int roleId = 0;
        while (result.next()) {
            roleId = result.getInt("id");
        }
        connectionManager.closeStatement(preparedStatement);
        connectionManager.closeConnection(dbConnection);

        return roleId;
    }
}