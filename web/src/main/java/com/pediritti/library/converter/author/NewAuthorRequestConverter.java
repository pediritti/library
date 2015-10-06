package com.pediritti.library.converter.author;

import com.pediritti.library.dto.author.request.NewAuthorRequest;
import com.pediritti.library.dtos.input.AuthorInputDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NewAuthorRequestConverter implements Converter<NewAuthorRequest, AuthorInputDTO> {

    @Override
    public AuthorInputDTO convert(NewAuthorRequest request) {
        return new AuthorInputDTO(request.getFirstName(), request.getLastName());
    }
}
