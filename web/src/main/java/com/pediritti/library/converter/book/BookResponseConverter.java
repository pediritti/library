package com.pediritti.library.converter.book;


import com.pediritti.library.dto.book.response.BookResponseDTO;
import com.pediritti.library.dtos.result.BookDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookResponseConverter implements Converter<BookDTO,BookResponseDTO> {
    @Override
    public BookResponseDTO convert(BookDTO bookDTO) {
        BookResponseDTO responseDTO = new BookResponseDTO();
        responseDTO.setId(23L);
        return responseDTO;
    }
}
