package com.pediritti.library.business.author;


import com.pediritti.library.domain.Author;
import com.pediritti.library.dtos.result.AuthorDTO;
import com.pediritti.library.business.ToDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorToDtoMapper extends ToDtoMapper<Author, AuthorDTO> {

    @Override
    public void setMapper() {
        mapper = (Author author) -> {
            AuthorDTO dto = new AuthorDTO();
            dto.setId(author.getId());
            dto.setFirstName(author.getFirstName());
            dto.setLastName(author.getLastName());
            return dto;
        };
    }
}
