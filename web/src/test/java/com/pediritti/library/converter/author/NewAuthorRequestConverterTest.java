package com.pediritti.library.converter.author;


import com.pediritti.library.dto.author.request.NewAuthorRequest;
import com.pediritti.library.dtos.input.AuthorInputDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NewAuthorRequestConverterTest {

    private static final String FIRST = "first";
    private static final String LAST = "last";

    private NewAuthorRequestConverter underTest;

    @Before
    public void setup() {
        underTest = new NewAuthorRequestConverter();
    }

    @Test
    public void testConversion() {
        NewAuthorRequest testInput = new NewAuthorRequest();
        testInput.setFirstName(FIRST);
        testInput.setLastName(LAST);

        AuthorInputDTO result = underTest.convert(testInput);

        assertEquals(FIRST, result.getFirstName());
        assertEquals(LAST, result.getLastName());
    }
}
