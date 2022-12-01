package org.example.repositories.dao.mysql.services;

import org.example.entities.good.Good;
import org.example.entities.good.GoodType;
import org.example.repositories.dao.AbstractDaoGoodService;
import org.example.repositories.dao.mysql.ConnectionManagerMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodServiceMySQL extends AbstractDaoGoodService {
    public GoodServiceMySQL(){
        super(new ConnectionManagerMySQL());
    }
    @Override
    protected void createInternal(Good good, Connection dbConnection) throws SQLException {
        int typeId = getTypeId(good.getType());
        String sql = """
                INSERT INTO `java_labs`.`good` (`id`, `name`, `type_id`, `price`, `amount`) VALUES (?, ?, ?, ?, ?); 
                """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setString(1, good.getId());
        preparedStatement.setString(2, good.getName());
        preparedStatement.setInt(3, typeId);
        preparedStatement.setFloat(4, good.getPrice());
        preparedStatement.setFloat(5, good.getAmount());
        preparedStatement.executeUpdate();

        connectionManager.closeStatement(preparedStatement);
    }

    @Override
    protected void deleteInternal(String id, Connection dbConnection) throws SQLException {
        String sql = """
            DELETE FROM `good`
            WHERE `id` = ?;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
        connectionManager.closeStatement(preparedStatement);
    }

    @Override
    protected void updateInternal(String id, Good newGood, Connection dbConnection) throws SQLException {
        int typeId = getTypeId(newGood.getType());
        String sql = """
            UPDATE `good`
            SET
            `name` = ?,
            `type_id` = ?,
            `price` = ?
            WHERE `id` = ?;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setString(1, newGood.getName());
        preparedStatement.setInt(2, typeId);
        preparedStatement.setFloat(3, newGood.getPrice());
        preparedStatement.setString(4, id);
        preparedStatement.executeUpdate();
        connectionManager.closeStatement(preparedStatement);
    }

    @Override
    protected List<Good> findAllInternal(Connection dbConnection) throws SQLException {
        List<Good> goods = new ArrayList<>();
        String sql = """
            SELECT `good`.`id`, `name`, `price`, `amount`, `good_type`.`type` FROM `good`
            INNER JOIN `good_type`
            ON `good`.`type_id` = `good_type`.`id`;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        ResultSet result = preparedStatement.executeQuery();
        while(result.next()) {
            Good good = setFields(result);
            goods.add(good);
        }
        connectionManager.closeStatement(preparedStatement);
        return goods;
    }

    @Override
    protected Good findByIdInternal(String id, Connection dbConnection) throws SQLException {
        Good good = new Good();
        String sql = """
            SELECT `good`.`id`, `name`, `price`, `amount`, `good_type`.`type` FROM `good`
            INNER JOIN `good_type`
            ON `good`.`type_id` = `good_type`.`id` WHERE `good`.`id` = ?;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            good = setFields(result);
        }
        connectionManager.closeStatement(preparedStatement);

        return good;
    }

    @Override
    public List<Good> findByTypeInternal (GoodType type, Connection dbConnection) throws SQLException {
        int typeId = getTypeId(type);
        List<Good> goods = new ArrayList<>();
        String sql = """
            SELECT `good`.`id`, `name`, `price`, `amount`, `good_type`.`type` FROM `good`
            INNER JOIN `good_type`
            ON `good`.`type_id` = `good_type`.`id` WHERE `type_id` = ?
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setInt(1, typeId);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            Good good = setFields(result);
            goods.add(good);
        }
        connectionManager.closeStatement(preparedStatement);

        return goods;
    }
    @Override
    public Good findByNameInternal (String name, Connection dbConnection) throws SQLException {
        Good good = new Good();
        String sql = """
            SELECT `good`.`id`, `name`, `price`, `amount`, `good_type`.`type` FROM `good`
            INNER JOIN `good_type`
            ON `good`.`type_id` = `good_type`.`id` WHERE `name` = ?
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            good = setFields(result);
        }
        connectionManager.closeStatement(preparedStatement);

        return good;
    }

    private Good setFields(ResultSet result) throws SQLException {
        Good good = new Good();

        good.setId(result.getString("id"));
        good.setName(result.getString("name"));
        good.setPrice(result.getInt("price"));
        good.setType(result.getString("type"));
        good.setAmount(result.getFloat("amount"));

        return good;
    }

    private int getTypeId(GoodType type) throws SQLException {
        Connection dbConnection = connectionManager.getConnection();
        String selectType = """
            SELECT `id` FROM `java_labs`.`good_type` WHERE `type` = ?
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(selectType);
        preparedStatement.setString(1, type.toString().toLowerCase());
        ResultSet result = preparedStatement.executeQuery();

        int typeId = 0;
        while (result.next()) {
            typeId = result.getInt("id");
        }
        connectionManager.closeStatement(preparedStatement);
        connectionManager.closeConnection(dbConnection);

        return typeId;
    }

}
