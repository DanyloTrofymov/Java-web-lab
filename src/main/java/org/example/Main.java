package org.example;

import org.example.controllers.MainController;
import org.example.repositories.dao.AbstractConnectionManager;
import org.example.repositories.dao.AbstractDaoGoodService;
import org.example.repositories.dao.AbstractDaoOrderService;
import org.example.repositories.dao.AbstractDaoUserService;
import org.example.repositories.dao.mysql.ConnectionManagerMySQL;
import org.example.repositories.dao.mysql.services.GoodServiceMySQL;
import org.example.repositories.dao.mysql.services.OrderServiceMySQL;
import org.example.repositories.dao.mysql.services.UserServiceMySQL;

public class Main {
    public static void main(String[] args) {
        AbstractConnectionManager connectionManager = new ConnectionManagerMySQL();
        AbstractDaoGoodService daoGoodService = new GoodServiceMySQL();
        AbstractDaoUserService daoUserService = new UserServiceMySQL();
        AbstractDaoOrderService daoOrderService = new OrderServiceMySQL();

        MainController controller = new MainController(connectionManager,daoGoodService, daoUserService, daoOrderService);
        controller.start();
    }
}