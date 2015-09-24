package com.pediritti.library.business.borrow.query;


import com.pediritti.library.business.AbstractParamQuery;
import com.pediritti.library.business.SingleQuery;
import com.pediritti.library.domain.Book;
import com.pediritti.library.domain.Borrowed;
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
