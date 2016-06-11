package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by FromxSoul on 08.06.2016.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public HibernateUtil() {

    }


    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
