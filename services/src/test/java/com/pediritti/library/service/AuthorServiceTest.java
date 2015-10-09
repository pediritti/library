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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestConfig.class)
@Transactional
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    private final AuthorInputDTO murakami = new AuthorInputDTO("Haruki", "Murakami");
    private final AuthorInputDTO ishiguro = new AuthorInputDTO("Kazuo", "Ishiguro");

    @Test
    public void testAuthorRegistration() {
        AuthorDTO result = authorService.registerAuthor(murakami);

        assertEquals(murakami.getFirstName(), result.getFirstName());
        assertEquals(murakami.getLastName(), result.getLastName());
        assertNotNull(result.getId());
        assertTrue(result.getId() > 0L);
    }

    @Test(expected = NoSuchElementException.class)
    public void testAuthorNotFoundByAuthorId() {
        authorService.findAuthor(0L);
    }

    @Test
    public void testGetAuthors() {
        authorService.registerAuthor(murakami);
        authorService.registerAuthor(ishiguro);

        List<AuthorDTO> authors = authorService.getAuthors();

        assertEquals(2, authors.size());
    }

    @Test
    public void testFindByName() {
        authorService.registerAuthor(murakami);

        List<AuthorDTO> authors = authorService.findAuthorByName("Haruki");

        assertEquals(1, authors.size());
}

    @Test
    public void testFindById() {
        AuthorDTO murakamiDto = authorService.registerAuthor(murakami);

        AuthorDTO author = authorService.findAuthor(murakamiDto.getId());

        assertEquals(murakami.getFirstName(), author.getFirstName());
        assertEquals(murakami.getLastName(), author.getLastName());
    }


    @Test
    public void testFindByLastNamePart() {
        authorService.registerAuthor(murakami);

        List<AuthorDTO> authors = authorService.findAuthorByName("Haru");

        assertEquals(1, authors.size());
    }

    @Test
    public void testFindByFirstNamePart() {
        authorService.registerAuthor(murakami);

        List<AuthorDTO> authors = authorService.findAuthorByName("uraka");

        assertEquals(1, authors.size());
    }

}
