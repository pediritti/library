package business.author;


import domain.Author;
import dtos.result.AuthorDTO;
import business.ToDtoMapper;
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
