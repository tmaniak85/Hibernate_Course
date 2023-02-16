package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.nullpointerexception.hibernate.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App47QueryCache {
    private static Logger logger = LogManager.getLogger(App30UpdateQuerying.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Customer> customer = em.createQuery("select c from Customer c", Customer.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
        logger.info(customer);
        em.getTransaction().commit();
        em.close();

        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        customer = em.createQuery("select c from Customer c", Customer.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
        logger.info(customer);
        em.getTransaction().commit();
        em.close();
    }
}
