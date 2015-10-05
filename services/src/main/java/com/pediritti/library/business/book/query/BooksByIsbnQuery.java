package com.pediritti.library.business.book.query;


import com.pediritti.library.domain.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BooksByIsbnQuery {
    List<Book> find(String isbn);
}
