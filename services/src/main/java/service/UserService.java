package service;

import business.user.PersonFactory;
import business.user.UserToDtoMapper;
import business.user.command.UserCommand;
import business.user.command.UserRegistrationCommand;
import business.user.query.UserByEmailQuery;
import domain.Person;
import dtos.input.UserInputDTO;
import dtos.result.UserDTO;
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
