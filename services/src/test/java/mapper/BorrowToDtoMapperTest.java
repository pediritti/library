package mapper;

import business.borrow.BorrowToDtoMapper;
import domain.Author;
import domain.Book;
import domain.Borrowed;
import domain.User;
import dtos.result.BorrowingDTO;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class BorrowToDtoMapperTest {

    private BorrowToDtoMapper underTest;

    @Before
    public void setup() {
        underTest = new BorrowToDtoMapper();
    }

    @Test
    public void testMapping() {
        long userId = 45L;
        long bookId = 78787878L;
        String title = "Romeo and Juliet";
        String authorFirstName = "Bill";
        String authorLastName = "Shakespeare";
        Date borrowDate = new Date();
        Date expectedReturnDate = new Date();

        Author author = new Author();
        author.setFirstName(authorFirstName);
        author.setLastName(authorLastName);

        Book book = new Book();
        book.setId(bookId);
        book.setTitle(title);
        book.setAuthor(author);

        User user = new User();
        user.setId(userId);

        Borrowed borrowed = new Borrowed();
        borrowed.setUser(user);
        borrowed.setBook(book);
        borrowed.setBorrowDate(borrowDate);
        borrowed.setExpectedReturnDate(expectedReturnDate);

        BorrowingDTO result = underTest.map(borrowed);

        assertEquals(userId, result.getUserId());
        assertEquals(bookId, result.getBookId());
        assertEquals(title, result.getTitle());
        assertEquals(authorFirstName + " " + authorLastName, result.getAuthor());
        assertEquals(new DateTime(borrowDate), result.getBorrowDate());
        assertEquals(new DateTime(expectedReturnDate), result.getExpectedReturnDate());
    }
}
