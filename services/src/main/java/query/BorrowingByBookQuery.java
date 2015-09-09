package query;


import domain.Borrowed;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class BorrowingByBookQuery {

    @PersistenceContext
    private EntityManager entityManager;
    private Query findByBook;

    public BorrowingByBookQuery() {
        findByBook = entityManager.createQuery(
                "TBD"
        );
    }

    public Borrowed find(long bookId) {
        findByBook.setParameter("bookId", bookId);
        Borrowed borrowed = (Borrowed) findByBook.getSingleResult();
        return borrowed;
    }
}
