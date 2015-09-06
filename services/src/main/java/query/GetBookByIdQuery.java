package query;

import domain.Book;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GetBookByIdQuery {

    @PersistenceContext
    EntityManager entityManager;

    public Book execute(long id) {
        return entityManager.find(Book.class, 1);
    }
}
