package com.pediritti.library.business.borrow.query;


import com.pediritti.library.domain.Book;
import com.pediritti.library.domain.Borrowed;

import java.util.Optional;

public interface BorrowingByBookQuery {
    Optional<Borrowed> find(Book book);
}
