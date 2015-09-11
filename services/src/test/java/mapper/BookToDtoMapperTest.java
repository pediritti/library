package mapper;


import domain.Author;
import domain.Book;
import dtos.BookDTO;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class BookToDtoMapperTest {

    private BookToDtoMapper underTest;

    @Before
    public void setUp() {
        underTest = new BookToDtoMapper();
    }

    @Test
    public void testMapping() {
        long bookId = 21L;
        long authorId = 67L;
        String title = "Test Title";
        String authorFirstName = "Edgar";
        String authorLastName = "Poe";
        String isbn = "1232343565747";
        Date issueDate = new Date();

        Author author = new Author();
        author.setId(authorId);
        author.setFirstName(authorFirstName);
        author.setLastName(authorLastName);

        Book book = new Book();
        book.setId(bookId);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setIssueDate(issueDate);

        BookDTO result = underTest.map(book);

        assertEquals(bookId, result.getId());
        assertEquals(authorId, result.getAuthorId());
        assertEquals(authorFirstName + " " + authorLastName, result.getAuthorName());
        assertEquals(title, result.getTitle());
        assertEquals(isbn, result.getIsbn());
        assertEquals(new DateTime(issueDate), result.getIssueDate());
    }
}
