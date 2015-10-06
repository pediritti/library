package com.pediritti.library.converter.book;


import com.pediritti.library.dto.book.response.BookResponse;
import com.pediritti.library.dtos.result.BookDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookResponseConverter implements Converter<BookDTO,BookResponse> {

    @Override
    public BookResponse convert(BookDTO dto) {
        BookResponse responseDTO = new BookResponse();
        responseDTO.setId(dto.getId());
        responseDTO.setAuthorName(dto.getAuthorName());
        responseDTO.setAuthorId(dto.getAuthorId());
        responseDTO.setIsbn(dto.getIsbn());
        responseDTO.setTitle(dto.getTitle());
        responseDTO.setIssueDate(dto.getIssueDate());
        return responseDTO;
    }
}
