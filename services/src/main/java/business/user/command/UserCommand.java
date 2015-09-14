package business.user.command;


import domain.Person;;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Person> find(long id) {
        return Optional.of(entityManager.find(Person.class, id));
    }
}
