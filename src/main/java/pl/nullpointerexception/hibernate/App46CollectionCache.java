package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App46CollectionCache {
    private static Logger logger = LogManager.getLogger(App30UpdateQuerying.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, 1L);
        logger.info(customer);
        logger.info(customer.getOrders());
        em.getTransaction().commit();
        em.close();

        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        customer = em.find(Customer.class, 1L);
        logger.info(customer);
        logger.info(customer.getOrders());
        em.getTransaction().commit();
        em.close();
    }
}
