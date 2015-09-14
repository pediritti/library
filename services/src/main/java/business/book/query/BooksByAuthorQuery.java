package business.book.query;

import business.AbstractParamQuery;
import domain.Author;
import domain.Book;
import business.ResultListQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
