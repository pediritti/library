package com.pediritti.library.converter.user;

import com.pediritti.library.dto.user.request.AddUserRequest;
import com.pediritti.library.dtos.input.UserInputDTO;
import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddUserRequestConverter implements Converter<AddUserRequest, UserInputDTO> {

    @Override
    public UserInputDTO convert(AddUserRequest request) {
        UserInputDTO userInputDTO = new UserInputDTO(request.getFirstName(),
                request.getLastName(), request.getPassword(), request.getEmail(),
                new DateTime(request.getBirth()), request.isAdmin());
        return userInputDTO;
    }
}
