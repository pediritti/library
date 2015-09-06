package mapper;

import domain.Book;
import dtos.BookDTO;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BookToDtoMapper implements ToDtoMapper<Book, BookDTO> {

    @Override
    public BookDTO map(Book entity) {
        return new BookDTO();
    }

    @Override
    public List<BookDTO> map(List<Book> entities) {
        return null;
    }

}
