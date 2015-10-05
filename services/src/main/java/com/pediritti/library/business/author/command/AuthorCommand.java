package com.pediritti.library.business.author.command;


import com.pediritti.library.domain.Author;

import java.util.Optional;

public interface AuthorCommand {

    Optional<Author> find(long id);
}
