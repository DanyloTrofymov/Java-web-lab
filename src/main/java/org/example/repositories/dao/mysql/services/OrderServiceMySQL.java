package org.example.repositories.dao.mysql.services;

import org.example.entities.order.Order;
import org.example.entities.order.OrderStatus;
import org.example.repositories.dao.AbstractCRUD;
import org.example.repositories.dao.AbstractOrderService;
import org.example.repositories.dao.mysql.ConnectionManagerMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceMySQL extends AbstractOrderService {
    public OrderServiceMySQL(){
        super(new ConnectionManagerMySQL());
    }
    @Override
    protected void createInternal(Order order, Connection dbConnection) throws SQLException {
        int statusId = getStatusId(order.getStatus());
        String sql = """
                INSERT INTO `java_labs`.`order` (`buyer_name`, `status_id`, `total_price`) VALUES (?, ?, ?);
                """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setString(1, order.getBuyerName());
        preparedStatement.setInt(2, statusId);
        preparedStatement.setFloat(3, order.getTotalPrice());
        preparedStatement.executeUpdate();

        connectionManager.closeStatement(preparedStatement);
    }

    @Override
    protected void deleteInternal(int id, Connection dbConnection) throws SQLException {
        String sql = """
            DELETE FROM `order`
            WHERE `id` = ?;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        connectionManager.closeStatement(preparedStatement);
    }

    @Override
    protected void updateInternal(int id, Order newOrder, Connection dbConnection) throws SQLException {
        int statusId = getStatusId(newOrder.getStatus());
        String sql = """
            UPDATE `order`
            SET
            `buyer_name` = ?, 
            `status_id` = ?,
            `total_price` = ?
            WHERE `id` = ?;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setString(1, newOrder.getBuyerName());
        preparedStatement.setInt(2, statusId);
        preparedStatement.setFloat(3, newOrder.getTotalPrice());
        preparedStatement.setInt(4, id);
        preparedStatement.executeUpdate();
        connectionManager.closeStatement(preparedStatement);
    }

    @Override
    protected List<Order> findAllInternal(Connection dbConnection) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = """
            SELECT `order`.`id`, `buyer_name`, `status_id`, `total_price`, `order_status`.`status` FROM `order`
            INNER JOIN `order_status`
            ON `order`.`status_id` = `order_status`.`id`;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        ResultSet result = preparedStatement.executeQuery();
        while(result.next()) {
            Order order = setFields(result);
            orders.add(order);
        }
        connectionManager.closeStatement(preparedStatement);
        return orders;
    }

    @Override
    protected Order findByIdInternal(int id, Connection dbConnection) throws SQLException {
        Order order = new Order();
        String sql = """
            SELECT `order`.`id`, `buyer_name`, `status_id`, `total_price`, `order_status`.`status` FROM `order`
            INNER JOIN `order_status`
            ON `order`.`status_id` = `order_status`.`id`  WHERE `order`.`id` = ?;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            order = setFields(result);
        }
        connectionManager.closeStatement(preparedStatement);

        return order;
    }

    @Override
    protected List<Order> findByStatusInternal (OrderStatus status, Connection dbConnection) throws SQLException {
        int statusId = getStatusId(status);
        List<Order> orders = new ArrayList<>();
        String sql = """
            SELECT * FROM `java_labs`.`order` WHERE `status_id` = ?
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setInt(1, statusId);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            Order order = setFields(result);
            orders.add(order);
        }
        connectionManager.closeStatement(preparedStatement);

        return orders;
    }

    private Order setFields(ResultSet result) throws SQLException {
        Order order = new Order();

        order.setId(result.getInt("id"));
        order.setBuyerName(result.getString("buyer_name"));
        order.setTotalPrice(result.getInt("total_price"));
        order.setStatus(result.getString("status"));

        return order;
    }

    private int getStatusId(OrderStatus status) throws SQLException {
        Connection dbConnection = connectionManager.getConnection();
        String orderStatus = """
            SELECT `id` FROM `java_labs`.`order_status` WHERE `status` = ?
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(orderStatus);
        preparedStatement.setString(1, status.toString().toLowerCase());
        ResultSet result = preparedStatement.executeQuery();

        int statusId = 0;
        while (result.next()) {
            statusId = result.getInt("id");
        }
        connectionManager.closeStatement(preparedStatement);
        connectionManager.closeConnection(dbConnection);

        return statusId;
    }
}

