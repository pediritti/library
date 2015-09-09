package query;


import domain.Book;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class BooksByAuthorQuery {

    @PersistenceContext
    private EntityManager entityManager;
    private Query findByAuthor;

    public BooksByAuthorQuery() {
        findByAuthor = entityManager.createQuery(
                "TBD"
        );
    }

    public List<Book> execute(long authorId) {
        findByAuthor.setParameter("authorId", authorId);
        List<Book> books = findByAuthor.getResultList();
        return books;
    }
}
