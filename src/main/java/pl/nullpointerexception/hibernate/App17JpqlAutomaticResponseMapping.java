package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Product;
import pl.nullpointerexception.hibernate.entity.ProductDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class App17JpqlAutomaticResponseMapping {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("select new pl.nullpointerexception.hibernate.entity.ProductDto(p.category.id, count(p)) from Product p group by p.category");

        List<ProductDto> resultList = query.getResultList();
        for (ProductDto productDto : resultList) {
            logger.info(productDto.getCategoryId());
            logger.info(productDto.getProductInCategoryCounter());
        }

        em.getTransaction().commit();
        em.close();
    }
}
