package mapper;

import domain.Book;
import dtos.BookDTO;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;


@Component
public class BookToDtoMapper extends ToDtoMapper<Book, BookDTO> {

    @Override
    void setMapper() {
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
