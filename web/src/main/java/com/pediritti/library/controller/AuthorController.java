package com.pediritti.library.controller;


import com.pediritti.library.business.author.command.AuthorCommand;
import com.pediritti.library.business.author.command.AuthorRegistrationCommand;
import com.pediritti.library.business.author.query.AuthorsAllQuery;
import com.pediritti.library.domain.Author;
import com.pediritti.library.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/author")
public class AuthorController {

    @Autowired
    private AuthorCommand authorCommand;
    @Autowired
    private AuthorRegistrationCommand authorRegistrationCommand;
    @Autowired
    private AuthorsAllQuery authorsAllQuery;


    @RequestMapping(method = RequestMethod.GET, value="/register")
    public String registerAuthor() {
        Author author = new Author("Edgar Allen", "Poe", Collections.<Book>emptyList());
        authorRegistrationCommand.create(author);
        return author.getLastName();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find")
    public String findAuthor() {
        Optional<Author> authorOptional = authorCommand.find(1L);
        if(authorOptional.isPresent()) {
            return authorOptional.get().getFirstName();
        } else {
            return "not found";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAll")
    public String findAll() {
        List<Author> authors = authorsAllQuery.findAll();
        return Integer.toString(authors.size());
    }
}
