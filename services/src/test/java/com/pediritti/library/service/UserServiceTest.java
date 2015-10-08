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
        userService.registerUser(userInputDTO);
    }

    @Test
    public void testFindByEmail() {
        UserDTO userDTO = userService.findUserByEmail("john.dow@mail.com");

        assertEquals(userInputDTO.getFirstName(), userDTO.getFirstName());
        assertEquals(userInputDTO.getLastName(), userDTO.getLastName());
        assertEquals(userInputDTO.getPassword(), userDTO.getPassword());
        assertEquals(userInputDTO.getEmail(), userDTO.getEmail());
        assertEquals(userInputDTO.getBirth(), userDTO.getBirth());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNotFoundByEmail() {
        userService.findUserByEmail("nobody@mail.com");
    }

    @Test
    public void testFindById() {
        UserDTO johnDow = userService.findUserByEmail("john.dow@mail.com");

        UserDTO userDTO = userService.findUser(johnDow.getId());

        assertEquals(userInputDTO.getFirstName(), userDTO.getFirstName());
        assertEquals(userInputDTO.getLastName(), userDTO.getLastName());
        assertEquals(userInputDTO.getPassword(), userDTO.getPassword());
        assertEquals(userInputDTO.getEmail(), userDTO.getEmail());
        assertEquals(userInputDTO.getBirth(), userDTO.getBirth());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNotFoundById() {
        userService.findUser(0L);
    }

}
