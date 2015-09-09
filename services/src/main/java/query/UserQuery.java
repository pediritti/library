package query;


import domain.Person;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class UserQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public Person find(long id) {
        return entityManager.find(Person.class, id);
    }
}
