package com.pediritti.library.controller;

import com.pediritti.library.converter.book.AddBookDTOConverter;
import com.pediritti.library.converter.book.BookResponseConverter;
import com.pediritti.library.dto.book.request.*;
import com.pediritti.library.dto.book.response.BookResponseDTO;
import com.pediritti.library.dtos.input.BookInputDTO;
import com.pediritti.library.dtos.result.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pediritti.library.service.BookService;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AddBookDTOConverter addBookDTOConverter;
    @Autowired
    private BookResponseConverter bookResponseConverter;

    @RequestMapping(method = RequestMethod.POST, value="/register")
    public void registerBook(AddBookRequestDTO request) {

        BookInputDTO bookInputDTO = addBookDTOConverter.convert(request);
        bookService.addBook(bookInputDTO);

    }

    @RequestMapping(method = RequestMethod.GET, value="/find")
    public BookResponseDTO find( @RequestParam(value="id", defaultValue = "0") long id) {
        BookDTO bookDTO = bookService.findBook(id);
        return bookResponseConverter.convert(bookDTO);
    }

    @RequestMapping(method = RequestMethod.POST, value="/findIsbn")
    public void findBooks(BookIsbnRequestDTO request) {
        bookService.findBooks(request.getIsbn());
    }

    @RequestMapping(method = RequestMethod.POST, value="/findTitle")
    public void findBooksByTitle(BookTitleRequestDTO request) {
        bookService.findBooksByTitle(request.getTitle());
    }

    @RequestMapping(method = RequestMethod.POST, value="/findAuthor")
    public void findBooksByAuthor(BookAuthorRequestDTO request) {
        bookService.findBooksByAuthor(request.getAuthorId());
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    public void updateBookTitle(BookTitleUpdateRequestDTO request) {
        List<BookDTO> books = bookService.findBooks(request.getIsbn());
        for(BookDTO book: books) {
            bookService.updateBookTitle(book.getId(), request.getTitle());
        }
    }

 }
