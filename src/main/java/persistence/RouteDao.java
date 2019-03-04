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

}
