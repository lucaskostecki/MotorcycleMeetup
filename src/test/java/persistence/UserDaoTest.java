package persistence;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
     * Verifies the getUsersByUsername returns a specified amount
     */
    @Test
    void getUsersByUsername() {
        List<User> users = dao.getUsersByUsername("testing");

        assertEquals(1, users.size());
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void insert() {
    }

    @Test
    void delete() {
    }
}