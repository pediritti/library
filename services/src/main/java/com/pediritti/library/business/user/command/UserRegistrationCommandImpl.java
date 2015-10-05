package com.pediritti.library.business.user.command;

import com.pediritti.library.domain.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRegistrationCommandImpl implements UserRegistrationCommand {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void create(Person user) {
        entityManager.persist(user);
    }
}
