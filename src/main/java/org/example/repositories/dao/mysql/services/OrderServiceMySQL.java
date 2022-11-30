package org.example.repositories.dao.mysql.services;

import org.example.entities.good.Good;
import org.example.entities.order.Order;
import org.example.entities.order.OrderStatus;
import org.example.repositories.dao.AbstractOrderService;
import org.example.repositories.dao.mysql.ConnectionManagerMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderServiceMySQL extends AbstractOrderService {
    public OrderServiceMySQL() {
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

        putGoodsInOrder(order, dbConnection);
    }

    @Override
    protected void deleteInternal(String id, Connection dbConnection) throws SQLException {
        String sql = """
            DELETE FROM `order` WHERE `id` = ?;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
        connectionManager.closeStatement(preparedStatement);

        deleteAllGoodsFromOrder(id, dbConnection);
    }

    @Override
    protected void updateInternal(String id, Order newOrder, Connection dbConnection) throws SQLException {
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
        preparedStatement.setString(4, id);
        preparedStatement.executeUpdate();
        connectionManager.closeStatement(preparedStatement);

        deleteAllGoodsFromOrder(newOrder.getId(), dbConnection);
        putGoodsInOrder(newOrder, dbConnection);
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
            Order order = setFields(result, dbConnection);
            orders.add(order);
        }
        connectionManager.closeStatement(preparedStatement);

        return orders;
    }

    @Override
    protected Order findByIdInternal(String id, Connection dbConnection) throws SQLException {
        Order order = new Order();
        String sql = """
            SELECT `order`.`id`, `buyer_name`, `status_id`, `total_price`, `order_status`.`status` FROM `order`
            INNER JOIN `order_status`
            ON `order`.`status_id` = `order_status`.`id`  WHERE `order`.`id` = ?;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            order = setFields(result, dbConnection);
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
            Order order = setFields(result, dbConnection);
            orders.add(order);
        }
        connectionManager.closeStatement(preparedStatement);

        return orders;
    }

    private List<Good> findGoods(String orderId,Connection dbConnection) throws SQLException{
        List<Good> goods = new ArrayList<>();
        String sql = """
            SELECT `good`.`id`,`good`.`name`, `good`.`price`, `good_type`.`type`, `good_in_order`.`good_amount` FROM `java_labs`.`good_in_order`\s
            INNER JOIN `good`\s
            ON `good_in_order`.`good_id` = `good`.`id`
            INNER JOIN `good_type`
            on `good`.`type_id` = `good_type`.`id` WHERE `order_id` = ?;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setString(1, orderId);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            Good good = new Good();
            good.setId(result.getString("id"));
            good.setName(result.getString("name"));
            good.setPrice(result.getFloat("price"));
            good.setType(result.getString("type"));
            good.setAmount(result.getFloat("good_amount"));
            goods.add(good);
        }
        connectionManager.closeStatement(preparedStatement);

        return  goods;
    }

    private void putGoodsInOrder(Order order, Connection dbConnection) throws SQLException{

        for(Good good : order.getGoods()) {
            String sql = """
            INSERT INTO `java_labs`.`good_in_order` (`good_id`, `good_amount`, `order_id`) VALUES (?, ?, ?);
            """;
            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, good.getId());
            preparedStatement.setFloat(2, good.getAmount());
            preparedStatement.setString(3, order.getId());
            preparedStatement.executeUpdate();
            connectionManager.closeStatement(preparedStatement);
        }
    }

    private void deleteAllGoodsFromOrder(String orderId, Connection dbConnection) throws SQLException{
        String sql = """
            DELETE FROM `good_in_order` WHERE `order_id` = ?;
            """;
        PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
        preparedStatement.setString(1, orderId);
        preparedStatement.executeUpdate();
        connectionManager.closeStatement(preparedStatement);
    }

    private Order setFields(ResultSet result, Connection dbConnection) throws SQLException {
        Order order = new Order();

        order.setId(result.getString("id"));
        order.setBuyerName(result.getString("buyer_name"));
        order.setTotalPrice(result.getInt("total_price"));
        order.setStatus(result.getString("status"));
        order.setGoods(findGoods(order.getId(), dbConnection));

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

