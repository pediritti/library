package mapper;


import business.user.UserToDtoMapper;
import domain.User;
import dtos.result.UserDTO;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class UserToDtoMapperTest {

    private UserToDtoMapper underTest;

    @Before
    public void setUp() {
        underTest = new UserToDtoMapper();
    }

    @Test
    public void testMapping() {
        long id = 34L;
        String firstName = "John";
        String lastName = "Dow";
        String password = "secret";
        String email = "john.dow@mail.com";
        Date birth = new Date();

        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setEmail(email);
        user.setBirth(birth);

        UserDTO result = underTest.map(user);

        assertEquals(id, result.getId());
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
        assertEquals(password, result.getPassword());
        assertEquals(email, result.getEmail());
        assertEquals(new DateTime(birth), result.getBirth());
    }
}
