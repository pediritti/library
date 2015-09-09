package command;


import domain.Author;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class AuthorRegistrationCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Author author) {
        entityManager.persist(author);
    }
}
