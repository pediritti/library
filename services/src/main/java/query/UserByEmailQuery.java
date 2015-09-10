package query;


import domain.Borrowed;
import domain.Person;
import domain.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Component
public class UserByEmailQuery extends AbstractParamQuery<Person, String> implements SingleQuery<Person, String> {

    private static final String FIELD_NAME = "email";

    public UserByEmailQuery() {
        init(Person.class, String.class, FIELD_NAME);
    }

    @Override
    public Person find(String email) {
        typedQuery.setParameter(parameter, email);
        return typedQuery.getSingleResult();
    }
}
