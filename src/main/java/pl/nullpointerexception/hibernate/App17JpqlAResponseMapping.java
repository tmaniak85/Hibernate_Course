package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.ProductDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class App17JpqlAResponseMapping {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("select p.category.id, count(p) from Product p group by p.category");

        List<Object[]> resultList = query.getResultList();
        List<ProductDto> result = resultList.stream()
                .map(objects -> new ProductDto((Long) objects[0], (Long) objects[1]))
                .collect(Collectors.toList());

        for (ProductDto productDto : result) {
            logger.info(productDto.getCategoryId());
            logger.info(productDto.getProductInCategoryCounter());
        }

        em.getTransaction().commit();
        em.close();
    }
}
