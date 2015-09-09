package command;

import domain.Borrowed;
import domain.Returned;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ReturnCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public void setReturned(Borrowed borrowed, Returned returned) {
        entityManager.remove(borrowed);
        entityManager.persist(returned);
    }

}
