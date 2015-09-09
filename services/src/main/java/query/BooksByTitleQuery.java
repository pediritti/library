package query;

import domain.Book;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class BooksByTitleQuery {

    @PersistenceContext
    private EntityManager entityManager;
    private Query findByTitle;

    public BooksByTitleQuery() {
        findByTitle = entityManager.createQuery(
                "TDB"
        );
    }

    public List<Book> execute(String title) {
        findByTitle.setParameter("title", title);
        List<Book> books = findByTitle.getResultList();
        return books;
    }
}
