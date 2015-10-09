package com.pediritti.library.converter.user;


import com.pediritti.library.dto.user.response.UserResponse;
import com.pediritti.library.dtos.result.UserDTO;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserResponseConverter implements Converter<UserDTO, UserResponse> {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    @Override
    public UserResponse convert(UserDTO userDTO) {
        UserResponse response = new UserResponse();
        response.setFirstName(userDTO.getFirstName());
        response.setLastName(userDTO.getLastName());
        response.setBirth(userDTO.getBirth().toString(dateTimeFormatter));
        response.setEmail(userDTO.getEmail());
        response.setId(userDTO.getId());
        return response;
    }
}
