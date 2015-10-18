package com.pediritti.library.service;

import com.pediritti.library.configuration.IntegrationTestConfig;
import com.pediritti.library.domain.Person;
import com.pediritti.library.dtos.input.PersonInputDTO;
import com.pediritti.library.dtos.result.PersonDTO;
import com.pediritti.library.util.ITestDataGenerator;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestConfig.class)
@Transactional
public class PersonServiceITest {

    @Autowired
    private PersonService personService;

    @Autowired
    private ITestDataGenerator testDataGenerator;

    private static final String BORROWER_FIRST_NAME = "John";
    private static final String BORROWER_LAST_NAME = "Dow";
    private static final String BORROWER_PASSWORD = "library";
    private static final String BORROWER_EMAIL = "john.dow@mail.com";
    private static final DateTime BORROWER_BIRTH = new DateTime(1968, 05, 30, 23, 59);
    private static final Boolean BORROWER_ADMIN = Boolean.FALSE;

    private static final long ADMIN_ID = 6299173L;
    private static final String ADMIN_FIRSTNAME = "Application";
    private static final String ADMIN_LASTNAME = "Admin";
    private static final String ADMIN_PASSWORD = "vf@ld}5ed";
    private static final String ADMIN_EMAIL = "app.admin@mail.com";
    private static final DateTime ADMIN_BIRTH = new DateTime(2000, 10, 10, 00, 00);
    private static final Boolean ADMIN_ADMIN = Boolean.TRUE;

    private static final String NON_EXISTING_EMAIL = "nobody@mail.com";
    private static final long NON_EXISTING_ID = 78485L;

    @After
    public void tearDown() {
        testDataGenerator.wipeAll();
    }

    @Test
    public void testRegister() {
        PersonInputDTO personInputDTO = new PersonInputDTO(BORROWER_FIRST_NAME, BORROWER_LAST_NAME,
                BORROWER_PASSWORD, BORROWER_EMAIL, BORROWER_BIRTH, BORROWER_ADMIN);

        PersonDTO result = personService.register(personInputDTO);

        assertEquals(BORROWER_FIRST_NAME, result.getFirstName());
        assertEquals(BORROWER_LAST_NAME, result.getLastName());
        assertEquals(BORROWER_PASSWORD, result.getPassword());
        assertEquals(BORROWER_EMAIL, result.getEmail());
        assertEquals(BORROWER_BIRTH, new DateTime(result.getBirth().getMillis()));
        assertFalse(result.isAdmin());
    }

    @Test
    public void testFindByEmail() {
        testDataGenerator.createPerson(new PersonInputDTO(BORROWER_FIRST_NAME,
                BORROWER_LAST_NAME, BORROWER_PASSWORD, BORROWER_EMAIL, BORROWER_BIRTH, BORROWER_ADMIN));

        PersonDTO result = personService.findByEmail(BORROWER_EMAIL);

        assertNotNull(result);
        assertEquals(BORROWER_FIRST_NAME, result.getFirstName());
        assertEquals(BORROWER_LAST_NAME, result.getLastName());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNotFoundByEmail() {
        personService.findByEmail(NON_EXISTING_EMAIL);
    }

    @Test
    public void testFindById() {

        Person person = testDataGenerator.createPerson(new PersonInputDTO(ADMIN_FIRSTNAME,
                ADMIN_LASTNAME, ADMIN_PASSWORD, ADMIN_EMAIL, ADMIN_BIRTH, ADMIN_ADMIN));

        PersonDTO result = personService.find(person.getId());

        assertEquals(ADMIN_FIRSTNAME, result.getFirstName());
        assertEquals(ADMIN_LASTNAME, result.getLastName());
        assertEquals(ADMIN_PASSWORD, result.getPassword());
        assertEquals(ADMIN_EMAIL, result.getEmail());
        assertEquals(ADMIN_BIRTH, new DateTime(result.getBirth().getMillis()));
        assertTrue(result.isAdmin());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNotFoundById() {
        personService.find(NON_EXISTING_ID);
    }

}
