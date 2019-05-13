package persistence;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.CleanDB;

import entity.Route;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User object dao test.
 */
class UserObjectDaoTest {

    private GenericDao dao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(User.class);
        CleanDB cleandb = CleanDB.getInstance();
        cleandb.runSQL("cleandb.sql");
    }

    /**
     * Gets all users.
     */
    @Test
    void getAllUsers() {
        List<User> users = dao.getAll();

        assertEquals(1, users.size());
    }

    /**
     * Gets users by username.
     */
    @Test
    void getUsersByUsername() {
        List<User> users = dao.getByPropertyLike("username", "testing");

        assertEquals(1, users.size());
    }

    /**
     * Save or update.
     */
    @Test
    void saveOrUpdate() {
        User targetUser = (User)dao.getById(1);
        targetUser.setUsername("new username");
        dao.saveOrUpdate(targetUser);
        User returnedUser = (User)dao.getById(1);
        assertEquals("new username", returnedUser.getUsername());
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        User user = new User(9, "testingFromInsert", "lucas.kostecki@gmail.com", "6085167408", "Lucas", "Kostecki", "testpass");
        int id = dao.insert(user);
        User insertedUser = (User)dao.getById(id);
        assertEquals("testingFromInsert", insertedUser.getUsername());
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