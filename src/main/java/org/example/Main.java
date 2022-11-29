package org.example;

import org.example.entities.good.Good;
import org.example.entities.good.GoodType;
import org.example.entities.user.Role;
import org.example.entities.user.User;
import org.example.repositories.dao.mysql.services.GoodServiceMySQL;
import org.example.repositories.dao.mysql.services.UserServiceMySQL;

import java.util.List;

public class Main {
    public static void main(String[] args){
        //UserServiceMySQL userMySQL = new UserServiceMySQL();

        //User user = userMySQL.findById(1);
        //System.out.println(user);

        //List<User> users = userMySQL.findAll();
        //System.out.println(users);

        //User user = new User("name", "surname", "username", "password", Role.getRole("EXPERT"));
        //userMySQL.create(user);

        //User user = new User("newName", "surname", "username", "password", Role.getRole("CASHER"));
        //userMySQL.update(3, user);

        //userMySQL.delete(3);

        GoodServiceMySQL goodMySQL = new GoodServiceMySQL();

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

        goodMySQL.delete(2);


    }
}