package com.pediritti.library.controller;

import com.pediritti.library.dto.borrow.request.BorrowRequest;
import com.pediritti.library.dto.borrow.request.BorrowerRequest;
import com.pediritti.library.dto.borrow.response.BorrowResponse;
import com.pediritti.library.dtos.result.BorrowingDTO;
import com.pediritti.library.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/borrow")
public class BorrowController {

    @Autowired
    private BorrowingService borrowingService;
    @Autowired
    private Converter<BorrowingDTO, BorrowResponse> borrowResponseConverter;

    @RequestMapping(method = RequestMethod.POST, value="/borrowBook")
    public void borrow(@RequestBody BorrowRequest request, HttpServletResponse response) {
        try {
            borrowingService.borrowBook(request.getBorrowerId(), request.getBookId());
        } catch (NoSuchElementException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value="/returnBook")
    public void returnBook(@RequestBody BorrowRequest request, HttpServletResponse response) {
        try {
            borrowingService.returnBook(request.getBorrowerId(), request.getBookId());
        } catch (NoSuchElementException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value="/list")
    public List<BorrowResponse> getBorrowings(@RequestBody BorrowerRequest request) {
        List<BorrowResponse> responseList = new ArrayList<>();
        for(BorrowingDTO dto : borrowingService.listActiveBorrowings(request.getBorrowerId())) {
            responseList.add(borrowResponseConverter.convert(dto));
        }
        return responseList;
    }
}
