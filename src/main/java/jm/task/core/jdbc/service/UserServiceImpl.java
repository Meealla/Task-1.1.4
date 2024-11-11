package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao DAO = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        DAO.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        DAO.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        DAO.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        DAO.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return DAO.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        DAO.cleanUsersTable();
    }
}
