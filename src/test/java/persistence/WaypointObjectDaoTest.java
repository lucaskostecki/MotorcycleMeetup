package persistence;

import entity.Route;
import entity.User;
import entity.Waypoint;
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
 * The type Waypoint object dao test.
 */
class WaypointObjectDaoTest {

    private GenericDao dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(Waypoint.class);
        CleanDB cleandb = CleanDB.getInstance();
        cleandb.runSQL("cleandb.sql");
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {

        Waypoint waypoint = (Waypoint) dao.getById(1);
        assertEquals("Waypoint name", waypoint.getWaypointName());

    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {
        List<Waypoint> waypoints = dao.getAll();

        assertEquals(2, waypoints.size());
    }

    /**
     * Gets by waypoint name.
     */
    @Test
    void getByWaypointName() {
        List<Waypoint> waypoints = dao.getByPropertyLike("waypointName", "Waypoint name");
        assertEquals(2, waypoints.size());

        waypoints = dao.getByPropertyLike("waypointName", "Waypoint name 2");
        assertEquals(1, waypoints.size());
    }

    /**
     * Save or update.
     */
    @Test
    void saveOrUpdate() {
        Waypoint targetWaypoint = (Waypoint) dao.getById(1);
        targetWaypoint.setWaypointName("New waypoint name");
        dao.saveOrUpdate(targetWaypoint);
        Waypoint returnedWaypoint = (Waypoint) dao.getById(1);
        assertEquals("New waypoint name", returnedWaypoint.getWaypointName());
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        dao = new GenericDao(Route.class);
        Route targetRoute = (Route) dao.getById(1);

        Waypoint waypoint = new Waypoint("New waypoint!", targetRoute);
        dao = new GenericDao(Waypoint.class);
        int id = dao.insert(waypoint);
        Waypoint insertedWaypoint = (Waypoint) dao.getById(id);
        assertEquals("New waypoint!", insertedWaypoint.getWaypointName());
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        dao.delete(dao.getById(2));
        assertNull(dao.getById(2));
        assertNotNull(dao.getById(1));
    }
}