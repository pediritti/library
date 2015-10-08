package com.pediritti.library.service;

import com.pediritti.library.configuration.IntegrationTestConfig;
import com.pediritti.library.dtos.input.AuthorInputDTO;
import com.pediritti.library.dtos.result.AuthorDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestConfig.class)
@Transactional
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    private final AuthorInputDTO murakami = new AuthorInputDTO("Haruki", "Murakami");

    @Before
    public void setup() {
        authorService.registerAuthor(murakami);
    }

    @Test(expected = NoSuchElementException.class)
    public void testAuthorNotFoundByAuthorId() {
        authorService.findAuthor(0L);
    }

    @Test
    public void testGetAuthors() {
        List<AuthorDTO> authors = authorService.getAuthors();

        assertTrue(authors.size() > 0);
    }

    @Test
    public void testFindByName() {
        List<AuthorDTO> authors = authorService.findAuthorByName("Haruki");

        assertEquals(1, authors.size());
}

    @Test
    public void testFindById() {
        List<AuthorDTO> authors = authorService.findAuthorByName("Haruki");

        long id = authors.get(0).getId();

        AuthorDTO author = authorService.findAuthor(id);

        assertEquals(murakami.getFirstName(), author.getFirstName());
        assertEquals(murakami.getLastName(), author.getLastName());
    }


    @Test
    public void testFindByLastNamePart() {
        List<AuthorDTO> authors = authorService.findAuthorByName("Haru");

        assertEquals(1, authors.size());
    }

    @Test
    public void testFindByFirstNamePart() {
        List<AuthorDTO> authors = authorService.findAuthorByName("uraka");

        assertEquals(1, authors.size());
    }

}
