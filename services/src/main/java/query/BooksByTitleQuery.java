package query;

import domain.Book;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class BooksByTitleQuery extends AbstractParamQuery<Book, String> implements ResultListQuery<Book, String> {

    private static final String FIELD_NAME = "title";

    public BooksByTitleQuery() {
        init(Book.class, String.class, FIELD_NAME);
    }

    @Override
    public List<Book> find(String title) {
        typedQuery.setParameter(parameter, title);
        return typedQuery.getResultList();
    }
}
