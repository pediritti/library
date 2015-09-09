package query;

import domain.Author;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class AuthorsAllQuery {

    @PersistenceContext
    private EntityManager entityManager;
    private Query findAllQuery;

    public AuthorsAllQuery() {
        findAllQuery = entityManager.createQuery("select a from author a");
    }

    public List<Author> findAll() {
        final List<Author> resultList = findAllQuery.getResultList();
        return resultList;
    }
}
