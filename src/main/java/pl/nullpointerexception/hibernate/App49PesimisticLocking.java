package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

public class App49PesimisticLocking {
    private static Logger logger = LogManager.getLogger(App30UpdateQuerying.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        Order order = em.find(Order.class, 3L, LockModeType.PESSIMISTIC_READ);
//        logger.info(order);

        Order singleResult = em.createQuery("select o from Order o where o.id= :id", Order.class)
                .setParameter("id", 3L)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getSingleResult();
        logger.info(singleResult);

        em.getTransaction().commit();
        em.close();
    }
}
