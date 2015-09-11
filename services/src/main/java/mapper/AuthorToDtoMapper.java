package mapper;


import domain.Author;
import dtos.AuthorDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorToDtoMapper extends ToDtoMapper<Author, AuthorDTO> {

    @Override
    void setMapper() {
        mapper = (Author author) -> {
            AuthorDTO dto = new AuthorDTO();
            dto.setId(author.getId());
            dto.setFirstName(author.getFirstName());
            dto.setLastName(author.getLastName());
            return dto;
        };
    }
}
