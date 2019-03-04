package persistence;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.CleanTestDB;

import entity.Route;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao dao;

    /**
     * Create the Dao
     */
    @BeforeEach
    void setUp() {
        dao = new UserDao();
        CleanTestDB cleandb = new CleanTestDB();
        cleandb.cleanDB();
    }

    /**
     * Verifies the getAllUsers returns all of the users in the DB
     */
    @Test
    void getAllUsers() {
        List<User> users = dao.getAllUsers();

        assertEquals(1, users.size());
    }

    /**
     * Verifies the getUsersByUsername returns a specified username
     */
    @Test
    void getUsersByUsername() {
        List<User> users = dao.getUsersByUsername("testing");

        assertEquals(1, users.size());
    }

    @Test
    void saveOrUpdate() {
        String newUsername = "new username";
        User updateUser = dao.getUserByID(1);
        updateUser.setUsername(newUsername);
        dao.saveOrUpdate(updateUser);
        User returnedUser = dao.getUserByID(1);
        assertEquals(newUsername, returnedUser.getUsername());
    }

    @Test
    void insert() {
        User user = new User(9, "testing1", "lucas.kostecki@gmail.com", "6085167408", "Lucas", "Kostecki");
        int id = dao.insert(user);
        assertNotEquals(0, id);
        List<User> insertedUser = dao.getUsersByUsername("testing1");
        assertEquals("testing1", insertedUser.get(0).getUsername());
    }

    @Test
    void insertWithOrder() {
        User newUser = new User(9, "testing2", "lekostecki@madisoncollege.edu", "5511553322", "Not", "Kostecki");
        String routeDescription = "Route 1";
        Route route = new Route(routeDescription, newUser);

        newUser.addRoute(route);

        int id = dao.insert(newUser);
        assertNotEquals(0, id);
        List<User> insertedUser = dao.getUsersByUsername("testing2");
        assertEquals("testing2", insertedUser.get(0).getUsername());
        assertEquals(1, insertedUser.get(0).getRoutes().size());
    }

    @Test
    void delete() {
        dao.delete(dao.getUserByID(1));
        assertNull(dao.getUserByID(1));
    }
}