package command;

import domain.Book;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Component
public class BookRegistrationCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Book book) {
        entityManager.persist(book);
    }

}
