package com.pediritti.library.business.user.query;


import com.pediritti.library.domain.Person;

import java.util.Optional;

public interface UserByEmailQuery {
    Optional<Person> find(String email);
}
