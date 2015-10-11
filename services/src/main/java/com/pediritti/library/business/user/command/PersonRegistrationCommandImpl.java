package com.pediritti.library.business.user.command;

import com.pediritti.library.domain.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PersonRegistrationCommandImpl implements PersonRegistrationCommand {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void create(Person person) {
        entityManager.persist(person);
    }
}
