package com.pediritti.library.converter.user;


import com.pediritti.library.dto.user.response.PersonResponse;
import com.pediritti.library.dtos.result.PersonDTO;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonResponseConverter implements Converter<PersonDTO, PersonResponse> {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    @Override
    public PersonResponse convert(PersonDTO dto) {
        PersonResponse response = new PersonResponse();
        response.setFirstName(dto.getFirstName());
        response.setLastName(dto.getLastName());
        response.setBirth(dto.getBirth().toString(dateTimeFormatter));
        response.setEmail(dto.getEmail());
        response.setId(dto.getId());
        response.setAdmin(dto.isAdmin());
        return response;
    }
}
