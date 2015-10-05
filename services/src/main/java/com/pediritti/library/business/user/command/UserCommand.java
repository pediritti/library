package com.pediritti.library.business.user.command;


import com.pediritti.library.domain.Person;

import java.util.Optional;

public interface UserCommand {
    Optional<Person> find(long id);
}
