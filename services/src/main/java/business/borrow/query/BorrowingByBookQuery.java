package business.borrow.query;


import business.AbstractParamQuery;
import business.SingleQuery;
import domain.Book;
import domain.Borrowed;
import org.springframework.stereotype.Repository;

@Repository
public class BorrowingByBookQuery extends AbstractParamQuery<Borrowed, Book> implements SingleQuery<Borrowed, Book> {

    private static final String FIELD_NAME = "book";

    public BorrowingByBookQuery() {
        init(Borrowed.class, Book.class, FIELD_NAME);
    }

    @Override
    public Borrowed find(Book book) {
        typedQuery.setParameter(parameter, book);
        return typedQuery.getSingleResult();
    }
}
