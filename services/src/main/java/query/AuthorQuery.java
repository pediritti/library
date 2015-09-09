package query;


import domain.Author;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class AuthorQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public Author find(long id) {
        return entityManager.find(Author.class, id);
    }

}
