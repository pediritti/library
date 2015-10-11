package com.pediritti.library.converter.user;

import com.pediritti.library.dto.user.request.AddPersonRequest;
import com.pediritti.library.dtos.input.PersonInputDTO;
import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddPersonRequestConverter implements Converter<AddPersonRequest, PersonInputDTO> {

    @Override
    public PersonInputDTO convert(AddPersonRequest request) {
        PersonInputDTO dto = new PersonInputDTO(request.getFirstName(),
                request.getLastName(), request.getPassword(), request.getEmail(),
                new DateTime(request.getBirth()), request.isAdmin());
        return dto;
    }
}
