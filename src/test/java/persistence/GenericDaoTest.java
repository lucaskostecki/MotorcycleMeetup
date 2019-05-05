package persistence;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.CleanTestDB;

import static org.junit.jupiter.api.Assertions.*;

class GenericDaoTest {

    GenericDao dao;

    /**
     * Clean test db
     */
    @BeforeEach
    void setUp() {
        dao = new GenericDao(User.class);
        CleanTestDB cleandb = new CleanTestDB();
        cleandb.cleanDB();
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void insert() {
    }

    @Test
    void saveOrUpdate() {
    }

    @Test
    void delete() {
    }

    @Test
    void getByPropertyLike() {
    }
}