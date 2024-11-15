package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Иван", "Иванов", (byte) 20);
        userService.saveUser("Василий", "Петров", (byte) 25);
        userService.saveUser("Мария", "Сергеева", (byte) 30);
        userService.saveUser("Галина", "Сидорова", (byte) 35);

        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        System.out.println("Пользователи удалены");
        userService.dropUsersTable();
        System.out.println("Таблица удалена");
        getSessionFactory().close();

//        userService.createUsersTable();
//        userService.saveUser("Иван", "Иванов", (byte) 20);
//        userService.saveUser("Василий", "Петров", (byte) 25);
//        userService.saveUser("Мария", "Сергеева", (byte) 30);
//        userService.saveUser("Галина", "Сидорова", (byte) 35);
//        for (int i = 0; i < userService.getAllUsers().size(); i++) {
//            System.out.println(userService.getAllUsers().get(i));
//        }
//        userService.cleanUsersTable();
//        System.out.println("Пользователи удалены");
//
//        userService.dropUsersTable();
//        System.out.println("Таблица удалена");


    }
}


