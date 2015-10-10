package com.pediritti.library.business.borrow;

import com.pediritti.library.domain.Borrowed;
import com.pediritti.library.dtos.result.BorrowingDTO;
import com.pediritti.library.business.ToDtoMapper;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class BorrowToDtoMapper extends ToDtoMapper<Borrowed, BorrowingDTO> {

    @Override
    public void setMapper() {
        mapper = (Borrowed borrowed) -> {
            BorrowingDTO dto = new BorrowingDTO();
            dto.setBookId(borrowed.getBook().getId());
            dto.setUserId(borrowed.getBorrower().getId());
            dto.setTitle(borrowed.getBook().getTitle());
            dto.setAuthor(getAuthorName(borrowed.getBook()));
            dto.setBorrowDate(new DateTime(borrowed.getBorrowDate()));
            dto.setExpectedReturnDate(new DateTime(borrowed.getExpectedReturnDate()));
            return dto;
        };

    }



}
