package com.pediritti.library.business.user;


import com.pediritti.library.domain.Admin;
import com.pediritti.library.domain.Person;
import com.pediritti.library.dtos.result.PersonDTO;
import com.pediritti.library.business.ToDtoMapper;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class PersonToDtoMapper extends ToDtoMapper<Person, PersonDTO> {

    @Override
    public void setMapper() {
        mapper = (Person person) -> {
            PersonDTO dto = new PersonDTO();
            dto.setId(person.getId());
            dto.setFirstName(person.getFirstName());
            dto.setLastName(person.getLastName());
            dto.setPassword(person.getPassword());
            dto.setEmail(person.getEmail());
            dto.setBirth(new DateTime(person.getBirth()));
            if(person instanceof Admin) {
                dto.setAdmin(true);
            } else {
                dto.setAdmin(false);
            }
            return dto;
        };
    }

}
