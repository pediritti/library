package query;


import domain.Author;
import domain.Book;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class BooksByAuthorQuery extends AbstractParamQuery<Book, Author> implements ResultListQuery<Book, Author> {

    private static final String FIELD_NAME = "author";

    public BooksByAuthorQuery() {
        init(Book.class, Author.class, FIELD_NAME);
    }

    @Override
    public List<Book> find(Author author) {
        typedQuery.setParameter(parameter, author);
        return typedQuery.getResultList();
    }
}
