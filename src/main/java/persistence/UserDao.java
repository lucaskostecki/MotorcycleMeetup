package persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import entity.User;

import javax.persistence.criteria.*;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;

/**
 * The type User dao.
 */
public class UserDao {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The Session factory.
     */
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets users by username.
     *
     * @param searchTerm the search term
     * @return the users by username
     */
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
