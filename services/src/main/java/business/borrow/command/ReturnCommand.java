package business.borrow.command;

import domain.Borrowed;
import domain.Returned;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ReturnCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public void setReturned(Borrowed borrowed, Returned returned) {
        entityManager.remove(borrowed);
        entityManager.persist(returned);
    }

}
