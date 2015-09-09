package query;

import domain.Book;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class BooksByIsbnQuery {

    @PersistenceContext
    private EntityManager entityManager;
    private Query findByIsbn;

    public BooksByIsbnQuery() {
        findByIsbn = entityManager.createQuery(
                "select object(book) from book where book.isbn = :isbn"
        );
    }

    public List<Book> execute(String isbn) {
        findByIsbn.setParameter("isbn", isbn);
        List<Book> books = findByIsbn.getResultList();
        return books;
    }
}
