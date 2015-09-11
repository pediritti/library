package mapper;


import domain.Person;
import dtos.UserDTO;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserToDtoMapper extends ToDtoMapper<Person, UserDTO> {

    @Override
    void setMapper() {
        mapper = (Person person) -> {
            UserDTO dto = new UserDTO();
            dto.setId(person.getId());
            dto.setFirstName(person.getFirstName());
            dto.setLastName(person.getLastName());
            dto.setPassword(person.getPassword());
            dto.setEmail(person.getEmail());
            dto.setBirth(new DateTime(person.getBirth()));
            return dto;
        };
    }

}
