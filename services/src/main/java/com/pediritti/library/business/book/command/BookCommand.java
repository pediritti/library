package com.pediritti.library.business.book.command;

import com.pediritti.library.domain.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BookCommand {
    Optional<Book> find(long id);
}
