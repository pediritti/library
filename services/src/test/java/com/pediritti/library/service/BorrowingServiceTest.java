package com.pediritti.library.service;

import com.pediritti.library.configuration.IntegrationTestConfig;
import com.pediritti.library.dtos.input.AuthorInputDTO;
import com.pediritti.library.dtos.input.BookInputDTO;
import com.pediritti.library.dtos.input.PersonInputDTO;
import com.pediritti.library.dtos.result.AuthorDTO;
import com.pediritti.library.dtos.result.BorrowingDTO;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestConfig.class)
@Transactional
public class BorrowingServiceTest {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private PersonService userService;
    @Autowired
    private BorrowingService borrowingService;

    private PersonInputDTO userInputDTO;
    private AuthorInputDTO pelevin;
    private BookInputDTO lifeOfInsects;
    private BookInputDTO buddhasLittleFinger;
    private long userId;
    private long lofId;

    @Before
    public void setup() {
        userInputDTO = new PersonInputDTO("John", "Dow", "library", "john.dow@mail.com",
                new DateTime(1968, 05, 30, 23, 59), false);
        userService.register(userInputDTO);
        userId = userService.findByEmail("john.dow@mail.com").getId();

        pelevin = new AuthorInputDTO("Victor", "Pelevin");
        authorService.registerAuthor(pelevin);

        List<AuthorDTO> authors = authorService.findAuthorByName("Pelevin");
        long pelevinId = authors.get(0).getId();

        lifeOfInsects = new BookInputDTO(pelevinId, "0140279725",
                "The Life of Insects", new DateTime(1999, 2, 1, 8, 0));
        buddhasLittleFinger = new BookInputDTO(pelevinId, "0141002328",
                "Buddha's Little Finger", new DateTime(2001, 12, 1, 8, 0));
        bookService.addBook(lifeOfInsects);
        bookService.addBook(buddhasLittleFinger);
        lofId = bookService.findBooks("0140279725").get(0).getId();

        borrowingService.borrowBook(userId, lofId);
    }

    @Test
    public void testBorrowings() {
        List<BorrowingDTO> borrowings = borrowingService.listActiveBorrowings(userId);

        assertEquals(1, borrowings.size());
        BorrowingDTO borrowingDTO = borrowings.get(0);
        assertEquals(pelevin.getFirstName() + " " + pelevin.getLastName(), borrowingDTO.getAuthor());
        assertEquals(lifeOfInsects.getTitle(), borrowingDTO.getTitle());
        assertEquals(lofId, borrowingDTO.getBookId());
        assertEquals(userId, borrowingDTO.getBorrowerId());
        //TODO: implement once return date logic is finalized
        //assertTrue((new DateTime()).isAfter(borrowingDTO.getExpectedReturnDate()));
        //assertTrue((new DateTime()).isAfter(borrowingDTO.getBorrowDate()));
    }

    @Test
    public void testReturn() {
        borrowingService.returnBook(userId, lofId);

        List<BorrowingDTO> borrowings = borrowingService.listActiveBorrowings(userId);
        assertEquals(0, borrowings.size());
    }

    @Test
    public void testReturnAndBorrow() {
        long blfId = bookService.findBooks("0141002328").get(0).getId();

        borrowingService.returnBook(userId, lofId);
        borrowingService.borrowBook(userId, blfId);

        List<BorrowingDTO> borrowings = borrowingService.listActiveBorrowings(userId);
        assertEquals(1, borrowings.size());

        BorrowingDTO borrowingDTO = borrowings.get(0);
        assertEquals(buddhasLittleFinger.getTitle(), borrowingDTO.getTitle());
        assertEquals(blfId, borrowingDTO.getBookId());
    }
}
