package com.pediritti.library.service;

import com.pediritti.library.configuration.IntegrationTestConfig;
import com.pediritti.library.domain.Author;
import com.pediritti.library.domain.Book;
import com.pediritti.library.domain.Borrower;
import com.pediritti.library.dtos.input.AuthorInputDTO;
import com.pediritti.library.dtos.input.BookInputDTO;
import com.pediritti.library.dtos.input.PersonInputDTO;
import com.pediritti.library.dtos.result.BorrowingDTO;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestConfig.class)
@Transactional
public class BorrowingServiceITest {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private PersonService userService;
    @Autowired
    private BorrowingService borrowingService;

    @Autowired
    private ITestDataGenerator testDataGenerator;

    private static final int SINGLE_RESULT = 1;
    private static final int TWO = 2;
    private static final long NON_EXISTING_ID = 0L;

    private static final String BORROWER_FIRST_NAME = "John";
    private static final String BORROWER_LAST_NAME = "Dow";
    private static final String BORROWER_PASSWORD = "library";
    private static final String BORROWER_EMAIL = "john.dow@mail.com";
    private static final DateTime BORROWER_BIRTH = new DateTime(1968, 05, 30, 23, 59);
    private static final Boolean BORROWER_ADMIN = Boolean.FALSE;

    private static final String AUTHOR_FIRST_NAME = "Victor";
    private static final String AUTHOR_LAST_NAME = "Pelevin";

    private static final String LOI_ISBN = "0140279725";
    private static final String LOI_TITLE = "the Life of Insects";
    private static final DateTime LOI_ISSUE = new DateTime(1999, 2, 1, 8, 0);

    private static final String BLF_ISBN = "0141002328";
    private static final String BLF_TITLE = "Buddha's Little Finger";
    private static final DateTime BLF_ISSUE = new DateTime(2001, 12, 1, 8, 0);

    private Author author;
    private Book bookLifeOfInsects;
    private Book bookBuddhasLittleFinger;
    private Borrower borrower;


    @Before
    public void setup() {
        borrower = (Borrower) testDataGenerator.createPerson(new PersonInputDTO(BORROWER_FIRST_NAME,
                BORROWER_LAST_NAME, BORROWER_PASSWORD, BORROWER_EMAIL, BORROWER_BIRTH, BORROWER_ADMIN));

        author = testDataGenerator.createAuthor(new AuthorInputDTO(AUTHOR_FIRST_NAME, AUTHOR_LAST_NAME));

        bookLifeOfInsects = testDataGenerator.createBook(new BookInputDTO(author.getId(), LOI_ISBN,
                LOI_TITLE, LOI_ISSUE), author);
        bookBuddhasLittleFinger = testDataGenerator.createBook(new BookInputDTO(author.getId(), BLF_ISBN,
                BLF_TITLE, BLF_ISSUE), author);
    }

    @After
    public void tearDown() {
        testDataGenerator.wipeAll();
    }

    @Test
    public void testBorrowings() {
        testDataGenerator.createBorrowing(borrower, bookLifeOfInsects);
        testDataGenerator.createBorrowing(borrower, bookBuddhasLittleFinger);

        List<BorrowingDTO> borrowings = borrowingService.listActiveBorrowings(borrower.getId());

        assertEquals(TWO, borrowings.size());
    }

    @Test
    public void testReturn() {
        testDataGenerator.createBorrowing(borrower, bookLifeOfInsects);
        testDataGenerator.createBorrowing(borrower, bookBuddhasLittleFinger);

        borrowingService.returnBook(borrower.getId(), bookLifeOfInsects.getId());

        List<BorrowingDTO> borrowings = borrowingService.listActiveBorrowings(borrower.getId());
        assertEquals(SINGLE_RESULT, borrowings.size());
    }

    @Test
    public void testBorrow() {
        borrowingService.borrowBook(borrower.getId(), bookBuddhasLittleFinger.getId());

        List<BorrowingDTO> borrowings = borrowingService.listActiveBorrowings(borrower.getId());

        assertEquals(SINGLE_RESULT, borrowings.size());
        BorrowingDTO result = borrowings.get(0);
        assertEquals(bookBuddhasLittleFinger.getId(), result.getBookId());
        assertEquals(AUTHOR_FIRST_NAME + " " + AUTHOR_LAST_NAME, result.getAuthor());
        assertEquals(bookBuddhasLittleFinger.getTitle(), result.getTitle());
    }
}
