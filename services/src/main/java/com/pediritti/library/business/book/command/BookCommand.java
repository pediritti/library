package com.pediritti.library.business.book.command;

import com.pediritti.library.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class BookCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Book> find(long id) {
        final Book book = entityManager.find(Book.class, id);
        if(book != null) {
            return Optional.of(book);
        } else {
            return Optional.empty();
        }
    }
}
