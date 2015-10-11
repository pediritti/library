package com.pediritti.library.controller;

import com.pediritti.library.dto.user.request.AddPersonRequest;
import com.pediritti.library.dto.user.request.PersonEmailRequest;
import com.pediritti.library.dto.user.request.PersonIdRequest;
import com.pediritti.library.dto.user.response.PersonResponse;
import com.pediritti.library.dtos.input.PersonInputDTO;
import com.pediritti.library.dtos.result.PersonDTO;
import com.pediritti.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private Converter<AddPersonRequest, PersonInputDTO> addPersonRequestConverter;
    @Autowired
    private Converter<PersonDTO, PersonResponse> personResponseConverter;

    @RequestMapping(method = RequestMethod.POST, value="/register")
    public PersonResponse register(@RequestBody AddPersonRequest request) {
        PersonInputDTO inputDTO = addPersonRequestConverter.convert(request);
        PersonDTO personDTO = personService.register(inputDTO);
        return personResponseConverter.convert(personDTO);
    }

    @RequestMapping(method = RequestMethod.POST, value="/findId")
    public PersonResponse findById(@RequestBody PersonIdRequest request, HttpServletResponse response) {
        PersonDTO dto;
        try {
            dto = personService.find(request.getId());
        } catch (NoSuchElementException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        return personResponseConverter.convert(dto);
    }

    @RequestMapping(method = RequestMethod.POST, value="/findEmail")
    public PersonResponse findByEmail(@RequestBody PersonEmailRequest request, HttpServletResponse response) {
        PersonDTO dto;
        try {
            dto = personService.findByEmail(request.getEmail());
        } catch (NoSuchElementException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        return personResponseConverter.convert(dto);
    }

}