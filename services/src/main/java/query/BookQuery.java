package query;

import domain.Book;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class BookQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public Book find(long id) {
        return entityManager.find(Book.class, id);
    }
}
