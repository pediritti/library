package com.pediritti.library.converter.author;


import com.pediritti.library.dto.author.response.AuthorResponse;
import com.pediritti.library.dtos.result.AuthorDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuthorResponseConverterTest {

    private static final long ID = 10L;
    private static final String FIRST = "first";
    private static final String LAST = "last";

    private AuthorResponseConverter underTest;

    @Before
    public void setup() {
        underTest = new AuthorResponseConverter();
    }

    @Test
    public void testConversion() {
        AuthorDTO testInput = new AuthorDTO();
        testInput.setFirstName(FIRST);
        testInput.setLastName(LAST);
        testInput.setId(ID);

        AuthorResponse result = underTest.convert(testInput);

        assertEquals(ID, result.getId());
        assertEquals(FIRST, result.getFirstName());
        assertEquals(LAST, result.getLastName());
    }
}
