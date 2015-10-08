package com.pediritti.library.service;


import com.pediritti.library.configuration.IntegrationTestConfig;
import com.pediritti.library.dtos.input.AuthorInputDTO;
import com.pediritti.library.dtos.input.BookInputDTO;
import com.pediritti.library.dtos.result.AuthorDTO;
import com.pediritti.library.dtos.result.BookDTO;
import org.joda.time.DateTime;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestConfig.class)
@Transactional
public class BookServiceTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    private final AuthorInputDTO frenzen = new AuthorInputDTO("Jonathan", "Frenzen");
    private long frenzenId;

    @Before
    public void setup() {
        authorService.registerAuthor(frenzen);
        List<AuthorDTO> authors = authorService.findAuthorByName("Frenzen");
        frenzenId = authors.get(0).getId();
        BookInputDTO theCorrections = new BookInputDTO(frenzenId, "0312421273", "The Corrections", new DateTime(2002, 9, 1, 8, 0));
        BookInputDTO freedom = new BookInputDTO(frenzenId, "0312576463", "Freedom", new DateTime(2011, 9, 27, 8, 0));
        bookService.addBook(theCorrections);
        bookService.addBook(freedom);
    }

    @Test
    public void testFindByAuthor() {
        List<BookDTO> books = bookService.findBooksByAuthor(frenzenId);

        assertEquals(2, books.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNotFoundByAuthor() {
        List<BookDTO> books = bookService.findBooksByAuthor(0L);

        assertEquals(2, books.size());
    }

    @Test
    public void testFindByIsbn() {
        List<BookDTO> books = bookService.findBooks("0312421273");

        assertEquals(1, books.size());
    }

    @Test
    public void testFindByTitle() {
        List<BookDTO> books = bookService.findBooksByTitle("Freedom");

        assertEquals(1, books.size());
    }

    @Test
    public void testUpdateTitle() {
        List<BookDTO> books = bookService.findBooksByTitle("The Corrections");
        long bookId = books.get(0).getId();
        bookService.updateBookTitle(bookId, "corrected");

        List<BookDTO> updatedBooks = bookService.findBooksByTitle("corrected");
        assertEquals(1, books.size());
    }

}
