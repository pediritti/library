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
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestConfig.class)
@Transactional
public class BookServiceTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    private AuthorInputDTO frenzen;
    private AuthorDTO frenzenDto;
    private BookInputDTO theCorrections;
    private BookInputDTO freedom;

    @Before
    public void setup() {
        frenzen = new AuthorInputDTO("Jonathan", "Frenzen");
        frenzenDto = authorService.registerAuthor(frenzen);

        theCorrections = new BookInputDTO(frenzenDto.getId(), "0312421273", "The Corrections", new DateTime(2002, 9, 1, 8, 0));
        freedom = new BookInputDTO(frenzenDto.getId(), "0312576463", "Freedom", new DateTime(2011, 9, 27, 8, 0));
    }

    @Test
    public void testBookRegistration() {
        BookDTO result = bookService.addBook(theCorrections);

        assertEquals(theCorrections.getAuthorId(), result.getAuthorId());
        assertEquals(theCorrections.getIsbn(), result.getIsbn());
        assertEquals(theCorrections.getTitle(), result.getTitle());
        assertEquals(theCorrections.getIssueDate(), result.getIssueDate());
        assertEquals(frenzen.getFirstName() + " " + frenzen.getLastName(), result.getAuthorName());
        assertTrue(result.getId() > 0L);
    }

    @Test
    public void testFindByAuthor() {
        bookService.addBook(theCorrections);
        bookService.addBook(freedom);

        List<BookDTO> books = bookService.findBooksByAuthor(frenzenDto.getId());

        assertEquals(2, books.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNotFoundByAuthor() {
        List<BookDTO> books = bookService.findBooksByAuthor(0L);
    }

    @Test
    public void testFindByIsbn() {
        bookService.addBook(theCorrections);

        List<BookDTO> books = bookService.findBooks("0312421273");

        assertEquals(1, books.size());
    }

    @Test
    public void testFindByTitle() {
        bookService.addBook(freedom);

        List<BookDTO> books = bookService.findBooksByTitle("Freedom");

        assertEquals(1, books.size());
    }

    @Test
    public void testUpdateTitle() {
        BookDTO bookDTO = bookService.addBook(theCorrections);

        bookService.updateBookTitle(bookDTO.getId(), "corrected");

        List<BookDTO> updatedBooks = bookService.findBooksByTitle("corrected");
        assertEquals(1, updatedBooks.size());
    }

}
