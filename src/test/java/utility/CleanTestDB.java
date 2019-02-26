package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import persistence.SessionFactoryProvider;

public class CleanTestDB {
    private final Logger logger = LogManager.getLogger(this.getClass());

    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public void cleanDB() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery("TRUNCATE MotorcycleMeetupTest.Users").executeUpdate();
        session.createNativeQuery("INSERT INTO MotorcycleMeetupTest.Users VALUES(null, 9, 'testing', null,'lucas.kostecki@gmail.com','6085167407','Lucas','Kostecki')").executeUpdate();
        transaction.commit();
        session.close();
    }
}