package com.pediritti.library.converter.user;

import com.pediritti.library.dto.user.response.UserResponse;
import com.pediritti.library.dtos.result.UserDTO;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserResponseConverterTest {

    private static final long ID = 23232L;
    private static final String BIRTH = "1968-01-01";
    private static final String EMAIL = "ME@MAIL.ORG";
    private static final String FIRST = "first";
    private static final String LAST = "last";
    private static final String PASSWORD = "s3cr3t";

    private UserResponseConverter underTest;

    @Before
    public void setup() {
        underTest = new UserResponseConverter();
    }

    @Test
    public void testConversion() {
        UserDTO testInput = new UserDTO();
        testInput.setBirth(new DateTime(BIRTH));
        testInput.setEmail(EMAIL);
        testInput.setFirstName(FIRST);
        testInput.setId(ID);
        testInput.setLastName(LAST);
        testInput.setPassword(PASSWORD);

        UserResponse result = underTest.convert(testInput);

        assertEquals(BIRTH, result.getBirth());
        assertEquals(EMAIL, result.getEmail());
        assertEquals(FIRST, result.getFirstName());
        assertEquals(LAST, result.getLastName());
        assertEquals(ID, result.getId());
    }
}
