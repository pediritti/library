package com.pediritti.library.business.book.query;


import com.pediritti.library.domain.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BooksByTitleQuery {

    List<Book> find(String title);
}
