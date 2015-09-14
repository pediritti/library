package business.borrow;

import domain.Borrowed;
import dtos.result.BorrowingDTO;
import business.ToDtoMapper;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class BorrowToDtoMapper extends ToDtoMapper<Borrowed, BorrowingDTO> {

    @Override
    public void setMapper() {
        mapper = (Borrowed borrowed) -> {
            BorrowingDTO dto = new BorrowingDTO();
            dto.setBookId(borrowed.getBook().getId());
            dto.setUserId(borrowed.getUser().getId());
            dto.setTitle(borrowed.getBook().getTitle());
            dto.setAuthor(getAuthorName(borrowed.getBook()));
            dto.setBorrowDate(new DateTime(borrowed.getBorrowDate()));
            dto.setExpectedReturnDate(new DateTime(borrowed.getExpectedReturnDate()));
            return dto;
        };

    }



}
