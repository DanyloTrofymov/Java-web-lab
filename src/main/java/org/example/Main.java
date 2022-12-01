package org.example;

import org.example.entities.user.User;
import org.example.entities.user.UserRole;
import org.example.repositories.dao.mysql.services.UserServiceMySQL;

import java.util.List;

public class Main {
    public static void main(String[] args){
        UserServiceMySQL userMySQL = new UserServiceMySQL();

        User user = userMySQL.findById("1");
        System.out.println(user);

        List<User> users = userMySQL.findAll();
        System.out.println(users);

        User user1 = new User("name", "surname", "username", "password", UserRole.getRole("EXPERT"));
        userMySQL.create(user1);

        //User user11 = new User("newName", "surname", "username", "password", UserRole.getRole("CASHER"));
        //userMySQL.update(, user11);

        //userMySQL.delete(4);

        //GoodServiceMySQL goodMySQL = new GoodServiceMySQL();

        //Good good = goodMySQL.findById(1);
        //System.out.println(good);

        //List<Good> goods = goodMySQL.findAll();
        //System.out.println(goods);

        //Good good = new Good("apple", GoodType.getType("FRUITS"), 100);
        //goodMySQL.create(good);
        //Good good1 = new Good("spoon", GoodType.getType("other"), 20);
        //goodMySQL.create(good1);

        //Good good = new Good("tomato", GoodType.getType("vegetables"), 101);
        //goodMySQL.update(2, good);

        //goodMySQL.delete(2);

        //OrderServiceMySQL orderMySQL = new OrderServiceMySQL();

        //Order order = orderMySQL.findById(4);
        //System.out.println(order);

        //List<Order> orders = orderMySQL.findAll();
        //System.out.println(orders);

        //Order order = new Order("Serhii Ivanov", OrderStatus.getStatus("registered"), 100);
        //orderMySQL.create(order);
        //Order order1 = new Order("Volodymyr Petrov", OrderStatus.getStatus("registered"), 1000);
        //orderMySQL.create(order1);

        //Order order = new Order("Serhii Petrov", 1000);
        //orderMySQL.update(3, order);

        //orderMySQL.delete(5);


        //GoodServiceMySQL goodMySQL = new GoodServiceMySQL();
        //OrderServiceMySQL orderMySQL = new OrderServiceMySQL();
        //ReportView view = new ReportView();
        //ReportService service = new ReportService(view, orderMySQL, goodMySQL);
        //service.report_X();

        //GoodServiceMySQL goodMySQL = new GoodServiceMySQL();
        //OrderServiceMySQL orderMySQL = new OrderServiceMySQL();
        //SeniorCashierView view = new SeniorCashierView();
        //OrderService o = new OrderService(orderMySQL);
        //GoodService g = new GoodService(goodMySQL);
        //ReportService r = new ReportService(orderMySQL, goodMySQL);
        //SeniorCashierController a = new SeniorCashierController(view, o, g, r);
        //a.editStatus();
    }
}