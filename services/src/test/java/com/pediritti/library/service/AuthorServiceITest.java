package com.pediritti.library.service;

import com.pediritti.library.configuration.IntegrationTestConfig;
import com.pediritti.library.domain.Author;
import com.pediritti.library.dtos.input.AuthorInputDTO;
import com.pediritti.library.dtos.result.AuthorDTO;
import com.pediritti.library.util.ITestDataGenerator;
import org.junit.After;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestConfig.class)
@Transactional
public class AuthorServiceITest {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private ITestDataGenerator testDataGenerator;

    private static final String MURAKAMI = "Murakami";
    private static final String HARUNKI = "Haruki";
    private static final String KAZUO = "Kazuo";
    private static final String ISHIGURO = "ishiguro";
    private static final String BANANA = "Banana";
    private static final String YOSHIMOTO = "Yoshimoto";

    private static final int SINGLE_RESULT = 1;
    private static final int TWO = 2;

    private static final long NON_EXISTING_ID = 0L;


    private Author murakamiEntity;
    private Author ishiguroEntity;

    @Before
    public void setup() {
        final AuthorInputDTO murakami = new AuthorInputDTO(HARUNKI, MURAKAMI);
        final AuthorInputDTO ishiguro = new AuthorInputDTO(KAZUO, ISHIGURO);
        murakamiEntity = testDataGenerator.createAuthor(murakami);
        ishiguroEntity = testDataGenerator.createAuthor(ishiguro);
    }

    @After
    public void tearDown() {
        testDataGenerator.wipeAll();
    }

    @Test
    public void testAuthorRegistration() {
        final AuthorInputDTO yoshimoto = new AuthorInputDTO(BANANA, YOSHIMOTO);
        AuthorDTO result = authorService.registerAuthor(yoshimoto);

        assertEquals(BANANA, result.getFirstName());
        assertEquals(YOSHIMOTO, result.getLastName());
        assertNotNull(result.getId());
    }

    @Test(expected = NoSuchElementException.class)
    public void testAuthorNotFoundByAuthorId() {
        authorService.findAuthor(NON_EXISTING_ID);
    }

    @Test
    public void testGetAuthors() {
        List<AuthorDTO> authors = authorService.getAuthors();

        assertEquals(TWO, authors.size());
    }

    @Test
    public void testFindByName() {

        List<AuthorDTO> authors = authorService.findAuthorByName(HARUNKI);

        assertEquals(SINGLE_RESULT, authors.size());
}

    @Test
    public void testFindById() {

        AuthorDTO author = authorService.findAuthor(murakamiEntity.getId());

        assertEquals(HARUNKI, author.getFirstName());
        assertEquals(MURAKAMI, author.getLastName());
    }


    @Test
    public void testFindByLastNamePart() {

        List<AuthorDTO> authors = authorService.findAuthorByName("azu");

        assertEquals(SINGLE_RESULT, authors.size());
    }

    @Test
    public void testFindByFirstNamePart() {

        List<AuthorDTO> authors = authorService.findAuthorByName("uraka");

        assertEquals(SINGLE_RESULT, authors.size());
    }

}
