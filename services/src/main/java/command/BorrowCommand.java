package command;


import domain.Borrow;
import domain.Borrowed;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BorrowCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Borrowed borrowed) {
        entityManager.persist(borrowed);
    }
}
