package business.borrow.command;


import domain.Borrow;
import domain.Borrowed;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BorrowCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Borrowed borrowed) {
        entityManager.persist(borrowed);
    }
}
