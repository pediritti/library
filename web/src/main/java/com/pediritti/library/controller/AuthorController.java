package com.pediritti.library.controller;


import com.pediritti.library.dto.author.request.AuthorIdRequest;
import com.pediritti.library.dto.author.request.AuthorNameRequest;
import com.pediritti.library.dto.author.request.NewAuthorRequest;
import com.pediritti.library.dto.author.response.AuthorResponse;
import com.pediritti.library.dtos.input.AuthorInputDTO;
import com.pediritti.library.dtos.result.AuthorDTO;
import com.pediritti.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private Converter<AuthorDTO, AuthorResponse> authorResponseConverter;
    @Autowired
    private Converter<NewAuthorRequest, AuthorInputDTO> newAuthorRequestConverter;

    @RequestMapping(method = RequestMethod.POST, value="/register")
    public void registerAuthor(@RequestBody NewAuthorRequest request) {
        AuthorInputDTO dto = newAuthorRequestConverter.convert(request);
        authorService.registerAuthor(dto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/find")
    public AuthorResponse findAuthor(@RequestBody AuthorIdRequest request) {
        AuthorDTO author = authorService.findAuthor(request.getAuthorId());
        return authorResponseConverter.convert(author);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/findByName")
    public List<AuthorResponse> findAuthorByName(@RequestBody AuthorNameRequest request) {
        List<AuthorDTO> authors = authorService.findAuthorByName(request.getAuthorName());
        return convert(authors);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAll")
    public List<AuthorResponse> findAll() {
        List<AuthorDTO> authors = authorService.getAuthors();
        return convert(authors);
    }

    private List<AuthorResponse> convert(List<AuthorDTO> authors) {
        List<AuthorResponse> responseList = new ArrayList<>(authors.size());
        for(AuthorDTO author: authors) {
            AuthorResponse response = authorResponseConverter.convert(author);
            responseList.add(response);
        }
        return responseList;
    }
}
