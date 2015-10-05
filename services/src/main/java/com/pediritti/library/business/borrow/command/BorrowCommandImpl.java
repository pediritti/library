package com.pediritti.library.business.borrow.command;


import com.pediritti.library.domain.Borrowed;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BorrowCommandImpl implements BorrowCommand {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void save(Borrowed borrowed) {
        entityManager.persist(borrowed);
    }
}
