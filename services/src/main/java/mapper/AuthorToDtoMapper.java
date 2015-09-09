package mapper;


import domain.Author;
import dtos.AuthorDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// TODO: Implement mapping
@Component
public class AuthorToDtoMapper implements ToDtoMapper<Author, AuthorDTO> {

    @Override
    public AuthorDTO map(Author entity) {
        return new AuthorDTO();
    }

    @Override
    public List<AuthorDTO> map(List<Author> entities) {
        return new ArrayList<AuthorDTO>();
    }
}
