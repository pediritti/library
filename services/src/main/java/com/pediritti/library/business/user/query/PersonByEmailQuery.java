package com.pediritti.library.business.user.query;


import com.pediritti.library.domain.Person;

import java.util.Optional;

public interface PersonByEmailQuery {
    Optional<Person> find(String email);
}
