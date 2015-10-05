package com.pediritti.library.business.user.command;


import com.pediritti.library.domain.Person;

public interface UserRegistrationCommand {
    void create(Person user);
}
