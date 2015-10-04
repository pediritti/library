package com.pediritti.library.business.user.command;


import com.pediritti.library.domain.Person;;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Person> find(long id) {
        Person person = entityManager.find(Person.class, id);
        if(person != null) {
            return Optional.of(person);
        } else {
            return Optional.empty();
        }
    }
}
