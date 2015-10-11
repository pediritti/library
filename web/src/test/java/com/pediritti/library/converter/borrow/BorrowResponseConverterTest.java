package com.pediritti.library.converter.borrow;

import com.pediritti.library.dto.borrow.response.BorrowResponse;
import com.pediritti.library.dtos.result.BorrowingDTO;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BorrowResponseConverterTest {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    private static final long USER_ID = 567445L;
    private static final long BOOK_ID = 875272829L;
    private static final String TITLE = "title";
    private static final String AUTHOR = "book writer";
    private static final DateTime BORROW_DATE = new DateTime();
    private static final DateTime EXP_RETURN_DATE = new DateTime();

    private BorrowResponseConverter underTest;

    @Before
    public void setup() {
        underTest = new BorrowResponseConverter();
    }

    @Test
    public void testConversion() {
        BorrowingDTO testInput = new BorrowingDTO();
        testInput.setAuthor(AUTHOR);
        testInput.setBookId(BOOK_ID);
        testInput.setBorrowDate(BORROW_DATE);
        testInput.setExpectedReturnDate(EXP_RETURN_DATE);
        testInput.setTitle(TITLE);
        testInput.setBorrowerId(USER_ID);

        BorrowResponse result = underTest.convert(testInput);

        assertEquals(AUTHOR, result.getAuthor());
        assertEquals(BOOK_ID, result.getBookId());
        assertEquals(BORROW_DATE.toString(dateTimeFormatter), result.getBorrowDate());
        assertEquals(EXP_RETURN_DATE.toString(dateTimeFormatter), result.getExpectedReturnDate());
        assertEquals(TITLE, result.getTitle());
        assertEquals(USER_ID, result.getBorrowerId());

    }
}
