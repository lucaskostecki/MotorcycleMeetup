package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import persistence.SessionFactoryProvider;

/**
 * A class used to clean the db for the JUnit tests
 *
 * @author Lucas Kostecki
 */
public class CleanTestDB {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Clean the database
     */
    public void cleanDB() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Create an SQL query to truncate the table. This way the ID's start at one while
        // removing all of the data in the table.
        session.createNativeQuery("SET FOREIGN_KEY_CHECKS  = 0").executeUpdate();
        session.createNativeQuery("TRUNCATE MotorcycleMeetupTest.Users").executeUpdate();
        session.createNativeQuery("TRUNCATE MotorcycleMeetupTest.Routes").executeUpdate();

        // Insert some default data to work with for each JUnit test
        session.createNativeQuery("INSERT INTO MotorcycleMeetupTest.Users VALUES(null, 9, 'testingye', null,'lucas.kostecki@gmail.com','6085167407','Lucas','Kostecki')").executeUpdate();
        session.createNativeQuery("INSERT INTO MotorcycleMeetupTest.Routes VALUES(null, 1, 'Some description')").executeUpdate();

        session.createNativeQuery("SET FOREIGN_KEY_CHECKS  = 1").executeUpdate();

        transaction.commit();
        session.close();
    }
}