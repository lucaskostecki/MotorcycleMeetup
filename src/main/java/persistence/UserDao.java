package persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import entity.User;

import javax.persistence.criteria.*;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDao {

    private final Logger logger = LogManager.getLogger(this.getClass());

    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        List<User> users = session.createQuery(query).getResultList();
        session.close();

        return users;
    }

    public List<User> getUsersByUsername(String searchTerm) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Expression<String> propertyPath = root.get("username");
        query.where(builder.like(propertyPath, "%" + searchTerm + "%"));
        List<User> users = session.createQuery(query).getResultList();
        session.close();

        return users;
    }

}
