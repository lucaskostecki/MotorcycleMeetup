package persistence;

import entity.Invite;
import entity.Route;
import entity.Waypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.CleanDB;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Invite object dao test.
 */
class InviteObjectDaoTest {

    private GenericDao dao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(Invite.class);
        CleanDB cleandb = CleanDB.getInstance();
        cleandb.runSQL("cleandb.sql");
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {
        Invite invite = (Invite) dao.getById(1);
        assertEquals("test@test.test", invite.getEmail());
    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {
        List<Invite> invites = dao.getAll();
        assertEquals(1, invites.size());
    }

    /**
     * Gets by invite email.
     */
    @Test
    void getByInviteEmail() {
        List<Invite> invites = dao.getByPropertyLike("email", "test@test.test");
        assertEquals(1, invites.size());
    }

    /**
     * Save or update.
     */
    @Test
    void saveOrUpdate() {
        Invite targetInvite = (Invite) dao.getById(1);
        targetInvite.setEmail("newemail@test.test");
        dao.saveOrUpdate(targetInvite);
        Invite returnedInvite = (Invite) dao.getById(1);
        assertEquals("newemail@test.test", returnedInvite.getEmail());
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        dao = new GenericDao(Route.class);
        Route targetRoute = (Route) dao.getById(1);

        Invite invite = new Invite("newemail@test.test", true, targetRoute);
        dao = new GenericDao(Invite.class);
        int id = dao.insert(invite);
        Invite insertedInvite = (Invite) dao.getById(id);
        assertEquals("newemail@test.test", insertedInvite.getEmail());
        assertTrue(insertedInvite.isPending());
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