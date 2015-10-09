package com.pediritti.library.service;

import com.pediritti.library.configuration.IntegrationTestConfig;
import com.pediritti.library.dtos.input.UserInputDTO;
import com.pediritti.library.dtos.result.UserDTO;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestConfig.class)
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private UserInputDTO userInputDTO;

    @Before
    public void setup() {
        userInputDTO = new UserInputDTO("John", "Dow", "library", "john.dow@mail.com",
                new DateTime(1968, 05, 30, 23, 59), false);
    }

    @Test
    public void testRegisterUser() {
        UserDTO result = userService.registerUser(userInputDTO);

        assertEquals(userInputDTO.getFirstName(), result.getFirstName());
        assertEquals(userInputDTO.getLastName(), result.getLastName());
        assertEquals(userInputDTO.getPassword(), result.getPassword());
        assertEquals(userInputDTO.getEmail(), result.getEmail());
        assertEquals(userInputDTO.getBirth(), result.getBirth());
        assertTrue(result.getId() > 0L);
    }

    @Test
    public void testFindByEmail() {
        userService.registerUser(userInputDTO);

        UserDTO userDTO = userService.findUserByEmail("john.dow@mail.com");

        assertNotNull(userDTO);
    }

    @Test(expected = NoSuchElementException.class)
    public void testNotFoundByEmail() {
        userService.findUserByEmail("nobody@mail.com");
    }

    @Test
    public void testFindById() {
        UserDTO registered = userService.registerUser(userInputDTO);

        UserDTO result = userService.findUser(registered.getId());

        assertEquals(userInputDTO.getFirstName(), result.getFirstName());
        assertEquals(userInputDTO.getLastName(), result.getLastName());
        assertEquals(userInputDTO.getPassword(), result.getPassword());
        assertEquals(userInputDTO.getEmail(), result.getEmail());
        assertEquals(userInputDTO.getBirth(), result.getBirth());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNotFoundById() {
        userService.findUser(0L);
    }

}
