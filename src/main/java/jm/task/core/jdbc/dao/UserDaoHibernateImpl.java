package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try(Session SESSION = Util.getSessionFactory().openSession()) {
            Transaction t = SESSION.beginTransaction();
            SESSION.createSQLQuery("CREATE TABLE IF NOT EXISTS User \n " +
                    "(id MEDIUMINT NOT NULL AUTO_INCREMENT, \n" +
                    "name VARCHAR(300) NOT NULL, lastName VARCHAR(300) NOT NULL, \n " +
                    "age TINYINT(3) NOT NULL, PRIMARY KEY(id));").executeUpdate();
            t.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session SESSION = Util.getSessionFactory().openSession()) {
            Transaction t = SESSION.beginTransaction();
            SESSION.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
            t.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction t = null;
        try (Session SESSION = Util.getSessionFactory().openSession()) {
            t = SESSION.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            SESSION.save(user);
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session SESSION = Util.getSessionFactory().openSession()) {
            Transaction t = SESSION.beginTransaction();
            SESSION.createSQLQuery("DELETE FROM User WHERE id=id");
            t.commit();
        }

    }

    @Override
    public List<User> getAllUsers() {
        try(Session SESSION = Util.getSessionFactory().openSession()) {
            List<User> users = (List<User>) SESSION.createQuery("From User").list();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session SESSION = Util.getSessionFactory().openSession()) {
            Transaction t = SESSION.beginTransaction();
            SESSION.createSQLQuery("TRUNCATE User").executeUpdate();
            t.commit();
        }

    }
}
