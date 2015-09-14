package service;

import business.author.AuthorFactory;
import business.author.AuthorToDtoMapper;
import business.author.command.AuthorCommand;
import business.author.command.AuthorRegistrationCommand;
import business.author.query.AuthorByNameQuery;
import business.author.query.AuthorsAllQuery;
import domain.Author;
import dtos.input.AuthorInputDTO;
import dtos.result.AuthorDTO;
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
    private AuthorToDtoMapper authorToDtoMapper;

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
