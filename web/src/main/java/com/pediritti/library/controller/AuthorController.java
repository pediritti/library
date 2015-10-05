package com.pediritti.library.controller;


import com.pediritti.library.business.author.command.AuthorCommandImpl;
import com.pediritti.library.business.author.command.AuthorRegistrationCommandImpl;
import com.pediritti.library.business.author.query.AuthorsAllQueryImpl;
import com.pediritti.library.domain.Author;
import com.pediritti.library.domain.Book;
import com.pediritti.library.dtos.input.AuthorInputDTO;
import com.pediritti.library.dtos.result.AuthorDTO;
import com.pediritti.library.service.AuthorService;
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
    private AuthorService authorService;


    @RequestMapping(method = RequestMethod.GET, value="/register")
    public String registerAuthor() {
        AuthorInputDTO dto = new AuthorInputDTO("Edgar Allen", "Poe");
        authorService.registerAuthor(dto);
        return dto.getLastName();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find")
    public String findAuthor() {
        AuthorDTO author = authorService.findAuthor(18L);
        return author.getLastName();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAll")
    public String findAll() {
        List<AuthorDTO> authors = authorService.getAuthors();
        return Integer.toString(authors.size());
    }
}
