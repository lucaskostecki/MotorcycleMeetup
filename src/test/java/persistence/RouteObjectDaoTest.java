package persistence;

import entity.Route;
import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.CleanDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Route object dao test.
 */
class RouteObjectDaoTest {

    private GenericDao dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(Route.class);
        CleanDB cleandb = CleanDB.getInstance();
        cleandb.runSQL("cleandb.sql");
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {

        Route route = (Route) dao.getById(1);
        assertEquals("Route Title", route.getTitle());

    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {
        List<Route> routes = dao.getAll();

        assertEquals(1, routes.size());
    }

    /**
     * Gets by route name.
     */
    @Test
    void getByRouteName() {
        List<Route> routes = dao.getByPropertyLike("title", "Route Title");

        assertEquals(1, routes.size());
    }

    /**
     * Save or update.
     */
    @Test
    void saveOrUpdate() {
        Route targetRoute = (Route) dao.getById(1);
        targetRoute.setTitle("New Title");
        dao.saveOrUpdate(targetRoute);
        Route returnedRoute = (Route) dao.getById(1);
        assertEquals("New Title", returnedRoute.getTitle());
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        dao = new GenericDao(User.class);
        User targetUser = (User) dao.getById(1);

        String strStartDate = "2019-05-20";
        Date startDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = sdf.parse(strStartDate);
        } catch (ParseException e) {
            logger.error("COULD NOT CONVERT DATE: " + e);
        }

        Route route = new Route("madison, wi", "middleton, wi", "Route Title", "Some description", false, startDate, "10:00:00", false,  "53562", targetUser);
        dao = new GenericDao(Route.class);
        int id = dao.insert(route);
        Route insertedRoute = (Route) dao.getById(id);
        assertEquals("madison, wi", insertedRoute.getStart());
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        dao.delete(dao.getById(1));
        assertNull(dao.getById(1));
    }
}