package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class App22FetchTypeInDirectFetchingAndQueryFetching {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        Product product = em.createQuery("select p from Product p where p.id=:id",
//                Product.class)
//                .setParameter("id", 1L)
//                .getSingleResult();

        Product product = em.find(Product.class, 1L);
        logger.info(product);
        logger.info(product.getCategory());

        em.getTransaction().commit();
        em.close();
    }
}
