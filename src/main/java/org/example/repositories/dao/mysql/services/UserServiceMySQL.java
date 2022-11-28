package org.example.repositories.dao.mysql.services;

import org.example.entities.user.Role;
import org.example.entities.user.User;
import org.example.repositories.dao.mysql.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserServiceMySQL implements org.example.repositories.dao.cruddao.CrudDao<User> {

    @Override
    public User insert(User user) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        try{
            dbConnection = ConnectionManager.getConnection();
            int roleId = getRoleId(user.getRole());
            String sql = """
                    INSERT INTO java_labs.user (username, password, firstname, lastname, roleid) VALUES (?,?,?,?,?)
                    """;
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setInt(5, roleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection(dbConnection);
        }

        return user;
    }

    @Override
    public void delete(int id) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        User user = new User();
        try{
            dbConnection = ConnectionManager.getConnection();
            String sql = """
                    DELETE FROM user
                    WHERE id = ?;
                    """;
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection(dbConnection);
        }
    }

    @Override
    public void update(int id, User newUser) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        try{
            int roleId = getRoleId(newUser.getRole());

            dbConnection = ConnectionManager.getConnection();
            String sql = """
                    UPDATE user
                    SET
                    `username` = ?,
                    `password` = ?,
                    `firstname` = ?,
                    `lastname` = ?,
                    `roleid` = ?
                    WHERE `id` = ?;
                    """;
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, newUser.getUsername());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.setString(3, newUser.getFirstname());
            preparedStatement.setString(4, newUser.getLastname());
            preparedStatement.setInt(5, roleId);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection(dbConnection);
        }
    }

    @Override
    public List<User> findAll() {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        List<User> users = new ArrayList<>();
        try{
            dbConnection = ConnectionManager.getConnection();
            String sql = """
                    SELECT `user`.`id`, `username`, `password`, `firstname`, `lastname`, `userrole`.`role` FROM `user`\s
                    INNER JOIN `userrole`
                    ON `user`.`roleid` = `userrole`.`id`;
                    """;
            preparedStatement = dbConnection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                User user = setFields(result);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection(dbConnection);
        }

        return users;
    }

    @Override
    public User findById(int id) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        User user = new User();
        try{
            dbConnection = ConnectionManager.getConnection();
            String sql = """
                    SELECT `user`.`id`, `username`, `password`, `firstname`, `lastname`, `userrole`.`role` FROM `user`\s
                    INNER JOIN `userrole`
                    ON `user`.`roleid` = `userrole`.`id` WHERE `user`.`id` = ?;
                    """;
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                user = setFields(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection(dbConnection);
        }

        return user;
    }

    private User setFields(ResultSet result) {
        User user = new User();
        try {
            user.setId(result.getInt("id"));
            user.setFirstname(result.getString("firstname"));
            user.setLastname(result.getString("lastname"));
            user.setRole(Role.getRole(result.getString("role")));
            user.setUsername(result.getString("username"));
            user.setPassword(result.getString("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private int getRoleId(Role role){
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        int roleid = 0;
        try {
            dbConnection = ConnectionManager.getConnection();
            String selectRole = """
                SELECT id FROM java_labs.userrole WHERE role=?
                """;
            preparedStatement = dbConnection.prepareStatement(selectRole);
            preparedStatement.setString(1, role.toString().toLowerCase());
            ResultSet result = preparedStatement.executeQuery();
            roleid = 0;
            while(result.next()){
                roleid = result.getInt("id");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeStatement(preparedStatement);
            ConnectionManager.closeConnection(dbConnection);
        }
        return roleid;
    }
}
