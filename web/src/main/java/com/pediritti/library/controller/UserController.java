package com.pediritti.library.controller;

import com.pediritti.library.dto.user.request.AddUserRequest;
import com.pediritti.library.dto.user.request.UserEmailRequest;
import com.pediritti.library.dto.user.request.UserIdRequest;
import com.pediritti.library.dto.user.response.UserResponse;
import com.pediritti.library.dtos.input.UserInputDTO;
import com.pediritti.library.dtos.result.UserDTO;
import com.pediritti.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private Converter<AddUserRequest, UserInputDTO> addUserRequestConverter;
    @Autowired
    private Converter<UserDTO, UserResponse> userResponseConverter;

    @RequestMapping(method = RequestMethod.POST, value="/register")
    public UserResponse registerBook(@RequestBody AddUserRequest request) {
        UserInputDTO userInputDTO = addUserRequestConverter.convert(request);
        UserDTO userDTO = userService.registerUser(userInputDTO);
        return userResponseConverter.convert(userDTO);
    }

    @RequestMapping(method = RequestMethod.POST, value="/findId")
    public UserResponse findById(@RequestBody UserIdRequest request, HttpServletResponse response) {
        UserDTO user;
        try {
            user = userService.findUser(request.getId());
        } catch (NoSuchElementException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        return userResponseConverter.convert(user);
    }

    @RequestMapping(method = RequestMethod.POST, value="/findEmail")
    public UserResponse findByEmail(@RequestBody UserEmailRequest request, HttpServletResponse response) {
        UserDTO user;
        try {
            user = userService.findUserByEmail(request.getEmail());
        } catch (NoSuchElementException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        return userResponseConverter.convert(user);
    }

}