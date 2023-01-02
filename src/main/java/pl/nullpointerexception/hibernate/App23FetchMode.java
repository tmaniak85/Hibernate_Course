package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Order;
import pl.nullpointerexception.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App23FetchMode {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        Order order = em.find(Order.class, 1L);
//        logger.info(order);
//        logger.info(order.getOrderRows());

//        List<Order> orders = em.createQuery("select o from Order o", Order.class).getResultList();
//        for (Order order : orders) {
//            logger.info(order);
//            logger.info(order.getOrderRows());
//        }

        List<Order> orders = em.createQuery("select o from Order o Order by o.created desc", Order.class)
                .setMaxResults(5)
                .getResultList();

        for (Order order : orders) {
            logger.info(order);
            logger.info(order.getOrderRows());
        }

        em.getTransaction().commit();
        em.close();
    }
}
