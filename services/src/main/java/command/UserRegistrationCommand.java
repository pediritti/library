package command;

import domain.Person;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class UserRegistrationCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Person user) {
        entityManager.persist(user);
    }
}
