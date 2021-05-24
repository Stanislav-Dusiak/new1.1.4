package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection CONNECTION = Util.connection;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = CONNECTION.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS User \n " +
                    "(id MEDIUMINT NOT NULL AUTO_INCREMENT, \n" +
                    "name VARCHAR(300) NOT NULL, lastName VARCHAR(300) NOT NULL, \n " +
                    "age TINYINT(3) NOT NULL, PRIMARY KEY(id));");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = CONNECTION.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS User");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement statement = CONNECTION.prepareStatement("INSERT INTO User \n" +
                    "(name, lastName, age) VALUES(?,?,?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement statement = CONNECTION.prepareStatement("DELETE FROM User WHERE id=?");
            statement.setLong(1, id);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        try {
            CONNECTION.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            CONNECTION.setAutoCommit(false);
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, lastName, age FROM User");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);
                allUsers.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = CONNECTION.createStatement();
            statement.executeUpdate("TRUNCATE User");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
