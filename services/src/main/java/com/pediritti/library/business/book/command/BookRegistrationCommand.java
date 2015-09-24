package com.pediritti.library.business.book.command;

import com.pediritti.library.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BookRegistrationCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Book book) {
        entityManager.persist(book);
    }

}
