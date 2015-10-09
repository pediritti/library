package com.pediritti.library.converter.user;


import com.pediritti.library.dto.user.request.AddUserRequest;
import com.pediritti.library.dtos.input.UserInputDTO;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class AddUserRequestConverterTest {

    private static final boolean ADMIN = false;
    private static final String BIRTH = "1968-01-01";
    private static final String EMAIL = "ME@MAIL.ORG";
    private static final String FIRST = "first";
    private static final String LAST = "last";
    private static final String PASSWORD = "s3cr3t";

    private AddUserRequestConverter underTest;

    @Before
    public void setup() {
        underTest = new AddUserRequestConverter();
    }

    @Test
    public void testConversion() {
        AddUserRequest testInput = new AddUserRequest();
        testInput.setAdmin(ADMIN);
        testInput.setBirth(BIRTH);
        testInput.setEmail(EMAIL);
        testInput.setFirstName(FIRST);
        testInput.setLastName(LAST);
        testInput.setPassword(PASSWORD);

        UserInputDTO result = underTest.convert(testInput);

        assertEquals(new DateTime(BIRTH), result.getBirth());
        assertEquals(EMAIL, result.getEmail());
        assertEquals(FIRST, result.getFirstName());
        assertEquals(LAST, result.getLastName());
        assertEquals(PASSWORD, result.getPassword());

    }
}
