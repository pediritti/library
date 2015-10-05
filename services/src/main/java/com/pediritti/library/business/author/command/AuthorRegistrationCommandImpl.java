package com.pediritti.library.business.author.command;


import com.pediritti.library.domain.Author;;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AuthorRegistrationCommandImpl implements AuthorRegistrationCommand {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void create(Author author) {
        entityManager.persist(author);
    }
}
