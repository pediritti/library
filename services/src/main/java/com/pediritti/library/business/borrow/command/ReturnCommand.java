package com.pediritti.library.business.borrow.command;

import com.pediritti.library.domain.Borrowed;
import com.pediritti.library.domain.Returned;
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
