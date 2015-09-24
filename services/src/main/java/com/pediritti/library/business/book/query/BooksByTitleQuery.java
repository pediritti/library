package com.pediritti.library.business.book.query;

import com.pediritti.library.business.AbstractParamQuery;
import com.pediritti.library.domain.Book;
import com.pediritti.library.business.ResultListQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
