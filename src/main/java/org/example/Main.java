package org.example;

import org.example.entities.user.Role;
import org.example.entities.user.User;
import org.example.repositories.dao.mysql.services.UserServiceMySQL;

import java.util.List;

public class Main {
    public static void main(String[] args){
        UserServiceMySQL sql = new UserServiceMySQL();
        //User user = sql.findById(1);
        //System.out.println(user);

        //List<User> users = sql.findAll();
        //System.out.println(users);


        //User user = new User("name", "surname", "username", "password", Role.getRole("EXPERT"));
        //sql.insert(user);

        //User user = new User("newName", "surname", "username", "password", Role.getRole("CASHER"));
        //sql.update(2, user);

        sql.delete(2);
    }
}