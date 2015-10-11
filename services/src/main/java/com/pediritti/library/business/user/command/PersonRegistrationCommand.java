package com.pediritti.library.business.user.command;


import com.pediritti.library.domain.Person;

public interface PersonRegistrationCommand {
    void create(Person person);
}
