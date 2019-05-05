package persistence;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.CleanTestDB;

import entity.Route;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserObjectDaoTest {

    GenericDao dao;

    @BeforeEach
    void setUp() {
        dao = new GenericDao(User.class);
        CleanTestDB cleandb = new CleanTestDB();
        cleandb.cleanDB();
    }

    /**
     * Verifies the getAllUsers returns all of the users in the DB
     */
    @Test
    void getAllUsers() {
        List<User> users = dao.getAll();

        assertEquals(1, users.size());
    }

    /**
     * Verifies the getUsersByUsername returns a specified username
     */
    @Test
    void getUsersByUsername() {
        List<User> users = dao.getByPropertyLike("username", "testing");

        assertEquals(1, users.size());
    }

    @Test
    void saveOrUpdate() {
        User targetUser = (User)dao.getById(1);
        targetUser.setUsername("new username");
        dao.saveOrUpdate(targetUser);
        User returnedUser = (User)dao.getById(1);
        assertEquals("new username", returnedUser.getUsername());
    }

    @Test
    void insert() {
        User user = new User(9, "testingFromInsert", "lucas.kostecki@gmail.com", "6085167408", "Lucas", "Kostecki", "testpass");
        int id = dao.insert(user);
        assertNotEquals(2, id);
        User insertedUser = (User)dao.getById(id);
        assertEquals("testingFromInsert", insertedUser.getUsername());
    }

    @Test
    void delete() {
        dao.delete(dao.getById(1));
        assertNull(dao.getById(1));
    }
}