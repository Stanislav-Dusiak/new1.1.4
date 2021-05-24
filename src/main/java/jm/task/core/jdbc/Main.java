package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Dave", "Jonson", (byte) 23);
        System.out.println("User с именем – Dave добавлен в базу данных");
        userService.saveUser("Rick", "Sanches", (byte) 68);
        System.out.println("User с именем – Rick добавлен в базу данных");
        userService.saveUser("Tinki", "Winki", (byte) 11);
        System.out.println("User с именем – Tinki добавлен в базу данных");
        userService.saveUser("Vasya", "Petrov", (byte) 75);
        System.out.println("User с именем – Dave добавлен в базу данных");

        List<User> users = userService.getAllUsers();

        for (User i : users) {
            System.out.println(i);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
