package com.pediritti.library.business.borrow.command;

import com.pediritti.library.domain.Borrowed;
import com.pediritti.library.domain.Returned;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ReturnCommandImpl implements ReturnCommand {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void setReturned(Borrowed borrowed, Returned returned) {
        entityManager.remove(borrowed);
        entityManager.persist(returned);
    }

}
