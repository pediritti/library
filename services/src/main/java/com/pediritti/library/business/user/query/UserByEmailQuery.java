package com.pediritti.library.business.user.query;


import com.pediritti.library.business.AbstractParamQuery;
import com.pediritti.library.business.SingleQuery;
import com.pediritti.library.domain.Person;

import org.springframework.stereotype.Repository;

@Repository
public class UserByEmailQuery extends AbstractParamQuery<Person, String> implements SingleQuery<Person, String> {

    private static final String FIELD_NAME = "email";

    public UserByEmailQuery() {
        init(Person.class, String.class, FIELD_NAME);
    }

    @Override
    public Person find(String email) {
        typedQuery.setParameter(parameter, email);
        return typedQuery.getSingleResult();
    }
}
