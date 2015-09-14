package business.user.command;

import domain.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRegistrationCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Person user) {
        entityManager.persist(user);
    }
}
