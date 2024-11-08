package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Connection con = Util.getConnection(); Statement statement = con.createStatement()) {
            String tableSql = """
                    CREATE TABLE IF NOT EXISTS User
                    (
                        id int PRIMARY KEY AUTO_INCREMENT NOT NULL ,
                        name varchar(30),
                        lastName varchar(30),
                        age int
                    )""";
            statement.execute(tableSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection con = Util.getConnection(); Statement statement = con.createStatement()) {
            String tableSql = """
                    DROP TABLE IF EXISTS User
                    """;
            statement.execute(tableSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = Util.getConnection();
             PreparedStatement statement = con.prepareStatement("INSERT INTO User (name, lastName, age) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            int userSave = statement.executeUpdate();
            if (userSave > 0) {
                System.out.println("User с именем — " + name + " добавлен в базу данных");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Connection con = Util.getConnection();
             PreparedStatement statement = con.prepareStatement("DELETE FROM User WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = Util.getConnection(); Statement statement = con.createStatement()) {
            String tableSql = "SELECT * FROM User";
            ResultSet resultSet = statement.executeQuery(tableSql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection con = Util.getConnection(); Statement statement = con.createStatement()) {
            String tableSql = "DELETE FROM User";
            statement.executeUpdate(tableSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

