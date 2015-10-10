package com.pediritti.library.converter.borrow;

import com.pediritti.library.dto.borrow.response.BorrowResponse;
import com.pediritti.library.dtos.result.BorrowingDTO;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BorrowResponseConverter implements Converter<BorrowingDTO, BorrowResponse> {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    @Override
    public BorrowResponse convert(BorrowingDTO dto) {
        BorrowResponse response = new BorrowResponse();
        response.setAuthor(dto.getAuthor());
        response.setBookId(dto.getBookId());
        response.setBorrowDate(dto.getBorrowDate().toString(dateTimeFormatter));
        response.setExpectedReturnDate(dto.getExpectedReturnDate().toString(dateTimeFormatter));
        response.setTitle(dto.getTitle());
        response.setUserId(dto.getUserId());
        return response;
    }
}
