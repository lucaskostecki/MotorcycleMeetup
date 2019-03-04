package persistence;

import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * The type Generic dao.
 *
 * @param <Type> the type parameter
 */
public class GenericDao<Type> {

    private Class<Type> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Generic dao.
     *
     * @param type the type
     */
    public GenericDao(Class<Type> type) {
        this.type = type;
    }

    /**
     * Gets entity by id.
     *
     * @param <Type> the type parameter
     * @param id     the id
     * @return an entity
     */
    public <Type>Type getById(int id) {
        Session session = getSession();
        Type entity = (Type)session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Gets all entities of type
     *
     * @return the all
     */
    public List<Type> getAll() {
        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Type> query = builder.createQuery(type);
        Root<Type> root = query.from(type);

        List<Type> list = session.createQuery(query).getResultList();
        session.close();

        return list;
    }

    /**
     * Insert or update a user
     *
     * @param entity    Entity to be inserted or updated
     * @return          The id of the entity inserted or updated
     */
    public int insert(Type entity) {
        int id = 0;

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        id = (int)session.save(entity);
        transaction.commit();

        session.close();
        return id;
    }

    /**
     * Save or update an entity
     *
     * @param entity the entity
     */
    public void saveOrUpdate(Type entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);

        transaction.commit();
        session.close();
    }

    /**
     * Delete an entity
     *
     * @param entity the entity
     */
    public void delete(Type entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);

        transaction.commit();
        session.close();
    }

    /**
     * Return an open session from SessionFactory
     * @return  session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

}
