package persistence;

import entity.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * The type Route dao.
 */
public class RouteDao {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The Session factory.
     */
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets all routes.
     *
     * @return the all routes
     */
    public List<Route> getAllRoutes() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Route> query = builder.createQuery(Route.class);
        Root<Route> root = query.from(Route.class);
        List<Route> routes = session.createQuery(query).getResultList();
        session.close();

        return routes;
    }

    /**
     * Gets routes by routename.
     *
     * @param searchTerm the search term
     * @return the routes by routename
     */
    public List<Route> getRoutesByRoutename(String searchTerm) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Route> query = builder.createQuery(Route.class);
        Root<Route> root = query.from(Route.class);
        Expression<String> propertyPath = root.get("routename");
        query.where(builder.like(propertyPath, "%" + searchTerm + "%"));
        List<Route> routes = session.createQuery(query).getResultList();
        session.close();

        return routes;
    }

    /**
     * Gets route by id.
     *
     * @param id the id
     * @return the route by id
     */
    public Route getRouteByID(int id) {
        Session session = sessionFactory.openSession();
        Route route = session.get(Route.class, id);
        session.close();

        return route;
    }

    /**
     * Save or update a route
     *
     * @param route Route to be inserted or updated
     */
    public void saveOrUpdate(Route route) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(route);
        transaction.commit();
        session.close();
    }

    /**
     * Insert or update a route
     *
     * @param route Route to be inserted or updated
     * @return The id of the route inserted or updated
     */
    public int insert(Route route) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(route);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a route from the DB
     *
     * @param route The route to be deleted from the DB
     */
    public void delete(Route route) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(route);
        transaction.commit();
        session.close();
    }

}
