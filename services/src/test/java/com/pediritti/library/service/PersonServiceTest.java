package com.pediritti.library.service;

import com.pediritti.library.configuration.IntegrationTestConfig;
import com.pediritti.library.dtos.input.PersonInputDTO;
import com.pediritti.library.dtos.result.PersonDTO;
import org.joda.time.DateTime;
import org.junit.Before;
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
public class PersonServiceTest {

    @Autowired
    private PersonService personService;
    private PersonInputDTO personInputDTO;

    @Before
    public void setup() {
        personInputDTO = new PersonInputDTO("John", "Dow", "library", "john.dow@mail.com",
                new DateTime(1968, 05, 30, 23, 59), false);
    }

    @Test
    public void testRegister() {
        PersonDTO result = personService.register(personInputDTO);

        assertEquals(personInputDTO.getFirstName(), result.getFirstName());
        assertEquals(personInputDTO.getLastName(), result.getLastName());
        assertEquals(personInputDTO.getPassword(), result.getPassword());
        assertEquals(personInputDTO.getEmail(), result.getEmail());
        assertEquals(personInputDTO.getBirth(), result.getBirth());
        assertEquals(personInputDTO.isAdmin(), result.isAdmin());
        assertTrue(result.getId() > 0L);
    }

    @Test
    public void testFindByEmail() {
        personService.register(personInputDTO);

        PersonDTO dto = personService.findByEmail("john.dow@mail.com");

        assertNotNull(dto);
    }

    @Test(expected = NoSuchElementException.class)
    public void testNotFoundByEmail() {
        personService.findByEmail("nobody@mail.com");
    }

    @Test
    public void testFindById() {
        PersonDTO registered = personService.register(personInputDTO);

        PersonDTO result = personService.find(registered.getId());

        assertEquals(personInputDTO.getFirstName(), result.getFirstName());
        assertEquals(personInputDTO.getLastName(), result.getLastName());
        assertEquals(personInputDTO.getPassword(), result.getPassword());
        assertEquals(personInputDTO.getEmail(), result.getEmail());
        assertEquals(personInputDTO.getBirth(), result.getBirth());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNotFoundById() {
        personService.find(0L);
    }

}
