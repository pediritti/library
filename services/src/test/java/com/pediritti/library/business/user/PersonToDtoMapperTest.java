package com.pediritti.library.business.user;


import com.pediritti.library.domain.Borrower;
import com.pediritti.library.dtos.result.PersonDTO;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PersonToDtoMapperTest {

    private PersonToDtoMapper underTest;

    @Before
    public void setUp() {
        underTest = new PersonToDtoMapper();
    }

    @Test
    public void testMapping() {
        long id = 34L;
        String firstName = "John";
        String lastName = "Dow";
        String password = "secret";
        String email = "john.dow@mail.com";
        Date birth = new Date();

        Borrower borrower = new Borrower();
        borrower.setId(id);
        borrower.setFirstName(firstName);
        borrower.setLastName(lastName);
        borrower.setPassword(password);
        borrower.setEmail(email);
        borrower.setBirth(birth);

        PersonDTO result = underTest.map(borrower);

        assertEquals(id, result.getId());
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
        assertEquals(password, result.getPassword());
        assertEquals(email, result.getEmail());
        assertEquals(new DateTime(birth), result.getBirth());
    }
}
