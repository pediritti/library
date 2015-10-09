package com.pediritti.library.converter.book;


import com.pediritti.library.dto.book.response.BookResponse;
import com.pediritti.library.dtos.result.BookDTO;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookResponseConverterTest {

    private static final long AUTHOR_ID = 34L;
    private static final long BOOK_ID = 34L;
    private static final String ISBN = "1234567890";
    private static final DateTime ISSUE_DATE = new DateTime();
    private static final String TITLE = "test title";
    private static final String AUTHOR_NAME = "book writer";

    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    private  BookResponseConverter underTest;

    @Before
    public void setup() {
        underTest = new BookResponseConverter();
    }

    @Test
    public void testConversion() {
        BookDTO testInput = new BookDTO();
        testInput.setAuthorId(AUTHOR_ID);
        testInput.setAuthorName(AUTHOR_NAME);
        testInput.setId(BOOK_ID);
        testInput.setIsbn(ISBN);
        testInput.setIssueDate(ISSUE_DATE);
        testInput.setTitle(TITLE);

        BookResponse result = underTest.convert(testInput);

        assertEquals(AUTHOR_ID, result.getAuthorId());
        assertEquals(AUTHOR_NAME, result.getAuthorName());
        assertEquals(BOOK_ID, result.getId());
        assertEquals(ISBN, result.getIsbn());
        assertEquals(ISSUE_DATE.toString(dateTimeFormatter), result.getIssueDate());
        assertEquals(TITLE, result.getTitle());

    }
}
