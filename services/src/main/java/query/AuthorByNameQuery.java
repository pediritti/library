package query;

import domain.Author;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class AuthorByNameQuery {

    @PersistenceContext
    private EntityManager entityManager;
    private Query findByName;

    public AuthorByNameQuery() {
        findByName = entityManager.createQuery(
                "TBD"
        );
    }

    public List<Author> find(String name) {
        findByName.setParameter("name", name);
        List<Author> authors = findByName.getResultList();
        return authors;
    }
}
