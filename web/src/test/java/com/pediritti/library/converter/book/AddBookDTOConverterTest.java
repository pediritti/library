package com.pediritti.library.converter.book;


import com.pediritti.library.dto.book.request.AddBookRequestDTO;
import com.pediritti.library.dtos.input.BookInputDTO;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddBookDTOConverterTest {

    private static final long AUTHOR_ID = 34L;
    private static final String ISBN = "1234567890";
    private static final String ISSUE_DATE = "2000-01-01";
    private static final String TITLE = "test title";

    private AddBookDTOConverter underTest;

    @Before
    public void setup() {
        underTest = new AddBookDTOConverter();
    }

    @Test
    public void testConversion() {
        AddBookRequestDTO testInput = new AddBookRequestDTO();
        testInput.setAuthorId(AUTHOR_ID);
        testInput.setIsbn(ISBN);
        testInput.setIssueDate(ISSUE_DATE);
        testInput.setTitle(TITLE);

        BookInputDTO result = underTest.convert(testInput);

        assertEquals(AUTHOR_ID, result.getAuthorId());
        assertEquals(ISBN, result.getIsbn());
        assertEquals(new DateTime(ISSUE_DATE), result.getIssueDate());
        assertEquals(TITLE, result.getTitle());
    }
}
