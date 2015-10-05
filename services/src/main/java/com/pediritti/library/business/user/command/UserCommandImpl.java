package com.pediritti.library.business.user.command;


import com.pediritti.library.domain.Person;;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserCommandImpl implements UserCommand {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Optional<Person> find(long id) {
        Person person = entityManager.find(Person.class, id);
        if(person != null) {
            return Optional.of(person);
        } else {
            return Optional.empty();
        }
    }
}
