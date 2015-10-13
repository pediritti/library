package com.pediritti.library.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.pediritti.library.configuration.IntegrationTestConfig;
import com.pediritti.library.dtos.input.PersonInputDTO;
import com.pediritti.library.dtos.result.PersonDTO;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.transaction.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestConfig.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@Transactional
@DatabaseSetup("/personTestData.xml")
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    private static final String EXPECTED_BORROWER_FIRSTNAME = "John";
    private static final String EXPECTED_BORROWER_LASTNAME = "Dow";
    private static final String EXPECTED_BORROWER_PASSWORD = "library";
    private static final String EXPECTED_BORROWER_EMAIL = "john.dow@mail.com";
    private static final DateTime EXPECTED_BORROWER_BIRTH = new DateTime(1968, 05, 30, 23, 59);

    private static final long EXPECTED_ADMIN_ID = 6299173L;
    private static final String EXPECTED_ADMIN_FIRSTNAME = "Application";
    private static final String EXPECTED_ADMIN_LASTNAME = "Admin";
    private static final String EXPECTED_ADMIN_PASSWORD = "vf@ld}5ed";
    private static final String EXPECTED_ADMIN_EMAIL = "app.admin@mail.com";
    private static final DateTime EXPECTED_ADMIN_BIRTH = new DateTime(2000, 10, 10, 00, 00);

    private static final String NON_EXISTING_EMAIL = "nobody@mail.com";
    private static final long NON_EXISTING_ID = 78485L;


    @Test
    public void testRegister() {
        PersonInputDTO personInputDTO = new PersonInputDTO("John", "Dow", "library", "john.dow@mail.com",
                new DateTime(1968, 05, 30, 23, 59), false);

        PersonDTO result = personService.register(personInputDTO);

        assertEquals(EXPECTED_BORROWER_FIRSTNAME, result.getFirstName());
        assertEquals(EXPECTED_BORROWER_LASTNAME, result.getLastName());
        assertEquals(EXPECTED_BORROWER_PASSWORD, result.getPassword());
        assertEquals(EXPECTED_BORROWER_EMAIL, result.getEmail());
        assertEquals(EXPECTED_BORROWER_BIRTH, new DateTime(result.getBirth().getMillis()));
        assertFalse(result.isAdmin());
    }

    @Test
    public void testFindByEmail() {

        PersonDTO result = personService.findByEmail(EXPECTED_BORROWER_EMAIL);

        assertNotNull(result);
        assertEquals(EXPECTED_BORROWER_FIRSTNAME, result.getFirstName());
        assertEquals(EXPECTED_BORROWER_LASTNAME, result.getLastName());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNotFoundByEmail() {
        personService.findByEmail(NON_EXISTING_EMAIL);
    }

    @Test
    public void testFindById() {

        PersonDTO result = personService.find(EXPECTED_ADMIN_ID);

        assertEquals(EXPECTED_ADMIN_FIRSTNAME, result.getFirstName());
        assertEquals(EXPECTED_ADMIN_LASTNAME, result.getLastName());
        assertEquals(EXPECTED_ADMIN_PASSWORD, result.getPassword());
        assertEquals(EXPECTED_ADMIN_EMAIL, result.getEmail());
        assertEquals(EXPECTED_ADMIN_BIRTH, new DateTime(result.getBirth().getMillis()));
        assertTrue(result.isAdmin());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNotFoundById() {
        personService.find(NON_EXISTING_ID);
    }

}
