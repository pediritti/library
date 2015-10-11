package com.pediritti.library.service;

import com.pediritti.library.business.ToDtoMapper;
import com.pediritti.library.business.user.PersonFactory;
import com.pediritti.library.business.user.command.PersonCommand;
import com.pediritti.library.business.user.command.PersonRegistrationCommand;
import com.pediritti.library.business.user.query.PersonByEmailQuery;
import com.pediritti.library.domain.Person;
import com.pediritti.library.dtos.input.PersonInputDTO;
import com.pediritti.library.dtos.result.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonCommand personCommand;
    @Autowired
    private PersonRegistrationCommand personRegistrationCommand;
    @Autowired
    private PersonByEmailQuery personByEmailQuery;
    @Autowired
    private ToDtoMapper<Person, PersonDTO> personToDtoMapper;

    @Transactional
    public PersonDTO find(long id) {
        Optional<Person> personOptional = personCommand.find(id);
        if(personOptional.isPresent()) {
            Person person = personOptional.get();
            return personToDtoMapper.map(person);
        } else {
            throw new NoSuchElementException("Person not found with id: " + id);
        }
    }

    @Transactional
    public PersonDTO findByEmail(String email) {
        Optional<Person> personOptional = personByEmailQuery.find(email);
        if(personOptional.isPresent()) {
            Person person = personOptional.get();
            return personToDtoMapper.map(person);
        } else {
            throw new NoSuchElementException("Person not found for email: " + email);
        }
    }

    @Transactional
    public PersonDTO register(PersonInputDTO inputDTO) {
        Person person = PersonFactory.createPerson(inputDTO.getFirstName(), inputDTO.getLastName(),
                inputDTO.getPassword(), inputDTO.getEmail(), inputDTO.getBirth(), inputDTO.isAdmin());
        personRegistrationCommand.create(person);
        return personToDtoMapper.map(person);

    }

}
