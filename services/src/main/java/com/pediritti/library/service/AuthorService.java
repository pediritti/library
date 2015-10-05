package com.pediritti.library.service;

import com.pediritti.library.business.ToDtoMapper;
import com.pediritti.library.business.author.AuthorFactory;
import com.pediritti.library.business.author.command.AuthorCommand;
import com.pediritti.library.business.author.command.AuthorRegistrationCommand;
import com.pediritti.library.business.author.query.AuthorByNameQuery;
import com.pediritti.library.business.author.query.AuthorsAllQuery;
import com.pediritti.library.domain.Author;
import com.pediritti.library.dtos.input.AuthorInputDTO;
import com.pediritti.library.dtos.result.AuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorCommand authorCommand;
    @Autowired
    private AuthorRegistrationCommand authorRegistrationCommand;
    @Autowired
    private AuthorByNameQuery authorByNameQuery;
    @Autowired
    private AuthorsAllQuery authorsAllQuery;
    @Autowired
    private ToDtoMapper<Author, AuthorDTO> authorToDtoMapper;

    @Transactional
    public List<AuthorDTO> getAuthors() {
        List<Author> authors = authorsAllQuery.findAll();
        return authorToDtoMapper.map(authors);
    }

    @Transactional
    public AuthorDTO findAuthor(long id) {
        Optional<Author> authorOptional = authorCommand.find(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            return authorToDtoMapper.map(author);
        } else {
            throw new NoSuchElementException("Author not found with id: " + id);
        }
    }

    @Transactional
    public List<AuthorDTO> findAuthorByName(String name) {
        List<Author> authors = authorByNameQuery.find(name);
        return authorToDtoMapper.map(authors);
    }

    @Transactional
    public void registerAuthor(AuthorInputDTO inputDTO) {
        Author author = AuthorFactory.createNew(inputDTO.getFirstName(), inputDTO.getLastName());
        authorRegistrationCommand.create(author);
    }

}
