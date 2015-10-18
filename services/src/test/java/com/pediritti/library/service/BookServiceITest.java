package com.pediritti.library.service;


import com.pediritti.library.configuration.IntegrationTestConfig;
import com.pediritti.library.domain.Author;
import com.pediritti.library.domain.Book;
import com.pediritti.library.dtos.input.AuthorInputDTO;
import com.pediritti.library.dtos.input.BookInputDTO;
import com.pediritti.library.dtos.result.BookDTO;
import com.pediritti.library.util.ITestDataGenerator;
import org.joda.time.DateTime;
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
public class BookServiceITest {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private ITestDataGenerator testDataGenerator;

    private static final String FRENZEN = "Frenzen";
    private static final String JONATHAN = "Jonathan";

    private static final String CORRECTIONS_ISBN = "0312421273";
    private static final String CORRECTIONS_TITLE = "The Corrections";
    private static final DateTime CORRECTIONS_ISSUE = new DateTime(2002, 9, 1, 8, 0);

    private static final String CORRECTED_TITLE = "corrected";

    private static final String FREEDOM_ISBN = "0312576463";
    private static final String FREEDOM_TITLE = "Freedom";
    private static final DateTime FREEDOM_ISSUE = new DateTime(2011, 9, 27, 8, 0);

    private static final int SINGLE_RESULT = 1;
    private static final int TWO = 2;
    private static final long NON_EXISTING_ID = 0L;

    private Author author;
    private Book correctionsBook;
    private Book freedomBook;

    @Before
    public void setup() {
        AuthorInputDTO frenzen = new AuthorInputDTO(JONATHAN, FRENZEN);
        author = testDataGenerator.createAuthor(frenzen);

        BookInputDTO theCorrections = new BookInputDTO(author.getId(), CORRECTIONS_ISBN, CORRECTIONS_TITLE, CORRECTIONS_ISSUE);
        BookInputDTO freedom = new BookInputDTO(author.getId(), FREEDOM_ISBN, FREEDOM_TITLE, FREEDOM_ISSUE);
        correctionsBook = testDataGenerator.createBook(theCorrections, author);
        freedomBook = testDataGenerator.createBook(freedom, author);
    }

    @After
    public void tearDown() {
        testDataGenerator.wipeAll();
    }

    @Test
    public void testBookRegistration() {
        BookInputDTO theCorrections = new BookInputDTO(author.getId(), CORRECTIONS_ISBN,
                CORRECTIONS_TITLE, CORRECTIONS_ISSUE);
        BookDTO result = bookService.addBook(theCorrections);

        assertEquals(theCorrections.getAuthorId(), result.getAuthorId());
        assertEquals(theCorrections.getIsbn(), result.getIsbn());
        assertEquals(theCorrections.getTitle(), result.getTitle());
        assertEquals(theCorrections.getIssueDate(), result.getIssueDate());
        assertEquals(author.getFirstName() + " " + author.getLastName(), result.getAuthorName());
        assertNotNull(result.getId());
    }

    @Test
    public void testFindByAuthor() {

        List<BookDTO> books = bookService.findBooksByAuthor(author.getId());

        assertEquals(TWO, books.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNotFoundByAuthor() {
        bookService.findBooksByAuthor(NON_EXISTING_ID);
    }

    @Test
    public void testFindByIsbn() {

        List<BookDTO> books = bookService.findBooks(CORRECTIONS_ISBN);

        assertEquals(SINGLE_RESULT, books.size());
    }

    @Test
    public void testFindByTitle() {

        List<BookDTO> books = bookService.findBooksByTitle(FREEDOM_TITLE);

        assertEquals(SINGLE_RESULT, books.size());
    }

    @Test
    public void testUpdateTitle() {

        bookService.updateBookTitle(correctionsBook.getId(), CORRECTED_TITLE);

        List<BookDTO> updatedBooks = bookService.findBooksByTitle(CORRECTED_TITLE);
        assertEquals(SINGLE_RESULT, updatedBooks.size());
    }

}
