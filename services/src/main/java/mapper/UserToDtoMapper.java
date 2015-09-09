package mapper;


import domain.Person;
import dtos.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserToDtoMapper implements ToDtoMapper<Person, UserDTO> {

    @Override
    public UserDTO map(Person entity) {
        return new UserDTO();
    }

    @Override
    public List<UserDTO> map(List<Person> entities) {
        return new ArrayList<UserDTO>();
    }
}
