package com.pediritti.library.business.book;

import com.pediritti.library.domain.Book;
import com.pediritti.library.dtos.result.BookDTO;
import com.pediritti.library.business.ToDtoMapper;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;


@Component
public class BookToDtoMapper extends ToDtoMapper<Book, BookDTO> {

    @Override
    public void setMapper() {
        mapper = (Book book) -> {
            BookDTO dto = new BookDTO();
            dto.setId(book.getId());
            dto.setAuthorId(book.getAuthor().getId());
            dto.setAuthorName(getAuthorName(book));
            dto.setIsbn(book.getIsbn());
            dto.setTitle(book.getTitle());
            dto.setIssueDate(new DateTime(book.getIssueDate()));
            return dto;
        };
    }


}
