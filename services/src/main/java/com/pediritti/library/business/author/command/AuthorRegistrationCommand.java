package com.pediritti.library.business.author.command;


import com.pediritti.library.domain.Author;;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AuthorRegistrationCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Author author) {
        entityManager.persist(author);
    }
}
