package com.pediritti.library.business.borrow.command;


import com.pediritti.library.domain.Borrowed;
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
