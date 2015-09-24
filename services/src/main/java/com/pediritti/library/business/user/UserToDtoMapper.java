package com.pediritti.library.business.user;


import com.pediritti.library.domain.Person;
import com.pediritti.library.dtos.result.UserDTO;
import com.pediritti.library.business.ToDtoMapper;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoMapper extends ToDtoMapper<Person, UserDTO> {

    @Override
    public void setMapper() {
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
