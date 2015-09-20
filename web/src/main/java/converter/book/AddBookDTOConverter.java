package converter.book;


import dto.book.request.*;
import dtos.input.BookInputDTO;
import org.springframework.core.convert.converter.Converter;


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
