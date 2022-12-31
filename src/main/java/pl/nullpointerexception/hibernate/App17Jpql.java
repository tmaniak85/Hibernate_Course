package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Category;
import pl.nullpointerexception.hibernate.entity.Product;
import pl.nullpointerexception.hibernate.entity.Review;
import pl.nullpointerexception.hibernate.entity.ReviewDto;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class App17Jpql {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        TypedQuery<Product> query = em.createQuery(
//                "select p from Product p" +
//                        " where p.name LIKE '%05%' order by p.id desc",
//                Product.class
//        );

//        TypedQuery<Product> query = em.createQuery(
//                "select p from Product p" +
//                        " where p.id=:id",
//                Product.class
//        );

//        Query query = em.createQuery(
//                "select avg(p.price) from Product p"
//        );
//
//        Double singleResult = (Double) query.getSingleResult();
//        logger.info(singleResult);

//        query.setParameter("id", 5L);

//        try {
//            Product product = query.getSingleResult();
//            logger.info(product);
//        } catch (NoResultException e) {
//            throw new RuntimeException("Brak wyników", e);
//        }

//        List<Product> resultList = query.getResultList();
//        for (Product product : resultList) {
//            logger.info(product);
//        }

//        Product product = query.getResultStream()
//                .findFirst()
//                .orElseThrow(RuntimeException::new);
//        logger.info(product);

//        Double singleResult = (Double) query.getSingleResult();
//        logger.info(singleResult);

//        Query query = em.createQuery(
//                "select count(p), avg(p.price) from Product p"
//        );
//
//        Object[] singleResult = (Object[]) query.getSingleResult();
//        logger.info(singleResult[0] + "-" + singleResult[1]);

//        Query query = em.createQuery(
//                "select avg(p.price) from Product p group by p.category"
//        );
//
//        List<Long> resultList = query.getResultList();
//        for (Long aLong : resultList) {
//            logger.info(aLong);
//        }

        Query query = em.createQuery(
                "select p.category, avg(p.price) from Product p group by p.category"
        );

        List<Object[]> resultList = query.getResultList();
        for (Object[] objects : resultList) {
            Category category = (Category) objects[0];
            logger.info(category.getName() + "-" + objects[1]);
        }

        em.getTransaction().commit();
        em.close();
    }
}
