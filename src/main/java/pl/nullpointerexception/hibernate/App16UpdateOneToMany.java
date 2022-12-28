package pl.nullpointerexception.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.nullpointerexception.hibernate.entity.Attribute;
import pl.nullpointerexception.hibernate.entity.Product;
import pl.nullpointerexception.hibernate.entity.Review;
import pl.nullpointerexception.hibernate.entity.ReviewDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class App16UpdateOneToMany {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<ReviewDto> reviewDtos = getUpdatedReviews();
        Product product = em.find(Product.class, 3L);
        Set<Review> reviews = product.getReviews();
        for (Review review : reviews) {
            for (ReviewDto reviewDto : reviewDtos) {
                if (review.getId().equals(reviewDto.getId())) {
                    review.setContent(reviewDto.getContent());
                    review.setRating(reviewDto.getRating());
                }
            }
        }

        em.getTransaction().commit();
        em.close();
    }

    private static List<ReviewDto> getUpdatedReviews() {
        List<ReviewDto> list = new ArrayList<>();
        list.add(new ReviewDto(13L, "Treść opinii 3!!", 10));
        list.add(new ReviewDto(14L, "Treść opinii 4!!", 10));
        list.add(new ReviewDto(15L, "Treść opinii 5!!", 10));
        return list;
    }
}
