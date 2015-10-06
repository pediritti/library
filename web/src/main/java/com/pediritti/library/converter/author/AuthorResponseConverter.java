package com.pediritti.library.converter.author;


import com.pediritti.library.dto.author.response.AuthorResponse;
import com.pediritti.library.dtos.result.AuthorDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorResponseConverter implements Converter<AuthorDTO, AuthorResponse> {

    @Override
    public AuthorResponse convert(AuthorDTO dto) {
        AuthorResponse response = new AuthorResponse();
        response.setId(dto.getId());
        response.setFirstName(dto.getFirstName());
        response.setLastName(dto.getLastName());
        return response;
    }
}
