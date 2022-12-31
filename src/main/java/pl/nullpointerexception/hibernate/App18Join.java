package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Product;
import pl.nullpointerexception.hibernate.entity.Review;
import pl.nullpointerexception.hibernate.entity.ReviewDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

public class App18Join {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        TypedQuery<Product> query = em.createQuery("select p from Product p" +
//                " inner join fetch p.category c" +
//                " where c.id=:id", Product.class);
//
//        query.setParameter("id", 1L);
//        List<Product> resultList = query.getResultList();
//        for (Product product : resultList) {
//            logger.info(product);
//            logger.info(product.getCategory());
//        }

        TypedQuery<Product> query = em.createQuery("select p from Product p" +
                " left join fetch p.category c", Product.class);

        List<Product> resultList = query.getResultList();
        for (Product product : resultList) {
            logger.info(product);
            logger.info(product.getCategory());
        }

        em.getTransaction().commit();
        em.close();
    }
}
