package business.book.query;

import business.AbstractParamQuery;
import domain.Book;
import business.ResultListQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BooksByIsbnQuery extends AbstractParamQuery<Book, String> implements ResultListQuery<Book, String> {

    private static final String FIELD_NAME = "isbn";

    public BooksByIsbnQuery() {
        init(Book.class, String.class, FIELD_NAME);
    }

    @Override
    public List<Book> find(String isbn) {
        typedQuery.setParameter(parameter, isbn);
        return typedQuery.getResultList();
    }
}
