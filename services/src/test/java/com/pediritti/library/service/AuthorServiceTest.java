package com.pediritti.library.service;


import com.pediritti.library.business.author.AuthorToDtoMapper;
import com.pediritti.library.business.author.command.AuthorCommand;
import com.pediritti.library.business.author.command.AuthorRegistrationCommand;
import com.pediritti.library.business.author.query.AuthorByNameQuery;
import com.pediritti.library.business.author.query.AuthorsAllQuery;
import com.pediritti.library.domain.Author;
import com.pediritti.library.dtos.input.AuthorInputDTO;
import com.pediritti.library.dtos.result.AuthorDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AuthorServiceTest {

    private static final long AUTHOR_ID = 666L;
    private static final String MURAKAMI = "Murakami";
    private static final String HARUNKI = "Haruki";
    private static final int SINGLE_RESULT = 1;
    private static final long NON_EXISTING_ID = 0L;

    private Author author;

    @InjectMocks
    private AuthorService underTest;

    @Mock
    private AuthorCommand authorCommand;
    @Mock
    private AuthorRegistrationCommand authorRegistrationCommand;
    @Mock
    private AuthorByNameQuery authorByNameQuery;
    @Mock
    private AuthorsAllQuery authorsAllQuery;
    @Mock
    private AuthorToDtoMapper authorToDtoMapper;

    @Before
    public void setup() {
        author = new Author();
        author.setId(AUTHOR_ID);
        author.setFirstName(MURAKAMI);
        author.setLastName(HARUNKI);
        initMocks(this);
    }

    @Test
    public void testRegisterAuthor() {
        AuthorInputDTO inputDTO = new AuthorInputDTO(MURAKAMI, HARUNKI);
        when(authorToDtoMapper.map(any(Author.class))).thenReturn(new AuthorToDtoMapper().map(author));

        AuthorDTO result = underTest.registerAuthor(inputDTO);

        assertEquals(MURAKAMI, result.getFirstName());
        assertEquals(HARUNKI, result.getLastName());
    }

    @Test
    public void testGetAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        when(authorsAllQuery.findAll()).thenReturn(authors);
        when(authorToDtoMapper.map(authors)).thenReturn(new AuthorToDtoMapper().map(authors));

        List<AuthorDTO> dtos = underTest.getAuthors();

        assertEquals(SINGLE_RESULT, dtos.size());
        AuthorDTO result = dtos.get(0);
        assertEquals(AUTHOR_ID, result.getId());
        assertEquals(MURAKAMI, result.getFirstName());
        assertEquals(HARUNKI, result.getLastName());
    }

    @Test
    public void testFindAuthor() {
        when(authorCommand.find(AUTHOR_ID)).thenReturn(Optional.of(author));
        when(authorToDtoMapper.map(author)).thenReturn(new AuthorToDtoMapper().map(author));

        AuthorDTO result = underTest.findAuthor(AUTHOR_ID);

        assertEquals(AUTHOR_ID, result.getId());
        assertEquals(MURAKAMI, result.getFirstName());
        assertEquals(HARUNKI, result.getLastName());
    }

    @Test(expected = NoSuchElementException.class)
    public void testAuthorNotFound() {
        when(authorCommand.find(NON_EXISTING_ID)).thenReturn(Optional.empty());

        underTest.findAuthor(NON_EXISTING_ID);
    }


    @Test
    public void testFindAuthorByName() {
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        when(authorByNameQuery.find(MURAKAMI)).thenReturn(authors);
        when(authorToDtoMapper.map(authors)).thenReturn(new AuthorToDtoMapper().map(authors));

        List<AuthorDTO> dtos = underTest.findAuthorByName(MURAKAMI);
        assertEquals(SINGLE_RESULT, dtos.size());
        AuthorDTO result = dtos.get(0);

        assertEquals(AUTHOR_ID, result.getId());
        assertEquals(MURAKAMI, result.getFirstName());
        assertEquals(HARUNKI, result.getLastName());
    }

}
