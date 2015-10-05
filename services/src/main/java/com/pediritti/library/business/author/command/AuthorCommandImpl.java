package com.pediritti.library.business.author.command;


import com.pediritti.library.domain.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class AuthorCommandImpl implements AuthorCommand {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Optional<Author> find(long id) {
        Author author = entityManager.find(Author.class, id);
        if(author != null) {
            return Optional.of(author);
        } else {
            return Optional.empty();
        }
    }

}
