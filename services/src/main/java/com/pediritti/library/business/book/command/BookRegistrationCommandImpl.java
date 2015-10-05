package com.pediritti.library.business.book.command;

import com.pediritti.library.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BookRegistrationCommandImpl implements BookRegistrationCommand {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void create(Book book) {
        entityManager.persist(book);
    }

}
