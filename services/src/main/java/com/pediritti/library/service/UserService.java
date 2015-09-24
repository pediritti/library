package com.pediritti.library.service;

import com.pediritti.library.business.user.PersonFactory;
import com.pediritti.library.business.user.UserToDtoMapper;
import com.pediritti.library.business.user.command.UserCommand;
import com.pediritti.library.business.user.command.UserRegistrationCommand;
import com.pediritti.library.business.user.query.UserByEmailQuery;
import com.pediritti.library.domain.Person;
import com.pediritti.library.dtos.input.UserInputDTO;
import com.pediritti.library.dtos.result.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserCommand userCommand;
    @Autowired
    private UserRegistrationCommand userRegistrationCommand;
    @Autowired
    private UserByEmailQuery userByEmailQuery;
    @Autowired
    private UserToDtoMapper userToDtoMapper;

    @Transactional
    public UserDTO findUser(long id) {
        Optional<Person> personOptional = userCommand.find(id);
        if(personOptional.isPresent()) {
            Person person = personOptional.get();
            return userToDtoMapper.map(person);
        } else {
            throw new NoSuchElementException("Person not found with id: " + id);
        }
    }

    @Transactional
    public UserDTO findUserByEmail(String email) {
        Person person = userByEmailQuery.find(email);
        return userToDtoMapper.map(person);
    }

    @Transactional
    public void registerUser(UserInputDTO inputDTO) {
        Person person = PersonFactory.createUser(inputDTO.getFirstName(), inputDTO.getLastName(),
                inputDTO.getPassword(), inputDTO.getEmail(), inputDTO.getBirth(), inputDTO.isAdmin());
        userRegistrationCommand.create(person);
    }

}
