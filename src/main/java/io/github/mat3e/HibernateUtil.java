package io.github.mat3e;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HibernateUtil {
    private static final SessionFactory sessionFactory = CreateSessionFactory();
    private static final Logger         logger         = LoggerFactory.getLogger(HibernateUtil.class);
   /**
    * Usage
    * Method returns SessionFactory entity which cannot be created
    * */
    static SessionFactory getSessionFactory(){return sessionFactory;}

    /**
     * Usage
     * Method close the SessionFactory
     */
    static void close() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }


    private static SessionFactory CreateSessionFactory()  {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            return new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            //logger.info("SessionFactory cannot be created");
            StandardServiceRegistryBuilder.destroy( registry );
            throw e;
        }
    }
    private HibernateUtil(){}
}
