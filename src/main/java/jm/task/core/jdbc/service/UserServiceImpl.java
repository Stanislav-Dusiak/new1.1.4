package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao USERDAO = new UserDaoHibernateImpl();
    public void createUsersTable() {
        USERDAO.createUsersTable();
    }

    public void dropUsersTable() {
        USERDAO.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        USERDAO.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        USERDAO.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return USERDAO.getAllUsers();
    }

    public void cleanUsersTable() {
        USERDAO.cleanUsersTable();
    }
}
