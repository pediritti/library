package com.pediritti.library.business.author.command;


import com.pediritti.library.domain.Author;


public interface AuthorRegistrationCommand {

    void create(Author author);
}
