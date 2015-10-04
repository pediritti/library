package com.pediritti.library.converter.book;


import com.pediritti.library.dto.book.request.*;
import com.pediritti.library.dtos.input.BookInputDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddBookDTOConverter implements Converter<AddBookRequestDTO, BookInputDTO> {

    @Override
    public BookInputDTO convert(AddBookRequestDTO request) {
        return new BookInputDTO(
                request.getAuthorId(),
                request.getIsbn(),
                request.getTitle(),
                request.getIssueDate()
        );
    }
}
