package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.nullpointerexception.hibernate.entity.Customer;
import pl.nullpointerexception.hibernate.entity.Order;
import pl.nullpointerexception.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class App21MultiMultiJoin {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery(
                "select distinct c.id, c.lastName as customer," +
                        " ca.name as category, SUM(orw.price) as total" +
                        " from Customer c" +
                        " inner join c.orders o" +
                        " inner join o.orderRows orw" +
                        " inner join orw.product p" +
                        " inner join p.category ca" +
                        " group by ca.id, c.id" +
                        " having sum(orw.price) > 50" +
                        " order by total desc");

        List<Object[]> resultList = query.getResultList();
        for (Object[] row : resultList) {
            logger.info(row[0] + "-" + row[1] + "-" + row[2] + "-" + row[3]);
        }

        em.getTransaction().commit();
        em.close();
    }
}
