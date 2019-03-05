package persistence;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.CleanTestDB;

import entity.Route;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao udao;
    GenericDao gdao;

    /**
     * Create the Udao
     */
    @BeforeEach
    void setUp() {
        udao = new UserDao();
        gdao = new GenericDao(User.class);
        CleanTestDB cleandb = new CleanTestDB();
        cleandb.cleanDB();
    }

    /**
     * Verifies the getAllUsers returns all of the users in the DB
     */
    @Test
    void getAllUsers() {
        List<User> users = gdao.getAll();

        assertEquals(1, users.size());
    }

    /**
     * Verifies the getUsersByUsername returns a specified username
     */
    @Test
    void getUsersByUsername() {
        List<User> users = udao.getUsersByUsername("testing");

        assertEquals(1, users.size());
    }

    @Test
    void saveOrUpdate() {
        String newUsername = "new username";
        User updateUser = (User)gdao.getById(1);
        updateUser.setUsername(newUsername);
        gdao.saveOrUpdate(updateUser);
        User returnedUser = (User)gdao.getById(1);
        assertEquals(newUsername, returnedUser.getUsername());
    }

    @Test
    void insert() {
        User user = new User(9, "testing1", "lucas.kostecki@gmail.com", "6085167408", "Lucas", "Kostecki");
        int id = gdao.insert(user);
        assertNotEquals(0, id);
        List<User> insertedUser = udao.getUsersByUsername("testing1");
        assertEquals("testing1", insertedUser.get(0).getUsername());
    }

    @Test
    void insertWithRoute() {
        User newUser = new User(9, "testing2", "lekostecki@madisoncollege.edu", "5511553322", "Not", "Kostecki");
        String routeDescription = "Route 1";
        Route route = new Route(routeDescription, newUser);

        newUser.addRoute(route);

        int id = gdao.insert(newUser);
        assertNotEquals(0, id);
        List<User> insertedUser = udao.getUsersByUsername("testing2");
        assertEquals("testing2", insertedUser.get(0).getUsername());
        assertEquals(1, insertedUser.get(0).getRoutes().size());
    }

    @Test
    void delete() {
        gdao.delete(gdao.getById(1));
        assertNull(gdao.getById(1));
    }
}