package query;


import domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class UserByEmailQuery {

    @PersistenceContext
    private EntityManager entityManager;
    private Query findByEmail;

    public UserByEmailQuery() {
        findByEmail = entityManager.createQuery(
                "select object(person) from person where person.email = :email"
        );
    }

    public Person find(String email) {
        findByEmail.setParameter("email", email);
        Person person = (Person)findByEmail.getSingleResult();
        return person;
    }
}
