package com.pediritti.library.controller;

import com.pediritti.library.converter.book.AddBookDTOConverter;
import com.pediritti.library.converter.book.BookResponseConverter;
import com.pediritti.library.dto.book.request.*;
import com.pediritti.library.dto.book.response.BookResponse;
import com.pediritti.library.dtos.input.BookInputDTO;
import com.pediritti.library.dtos.result.BookDTO;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pediritti.library.service.BookService;

import java.util.ArrayList;
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
 //   public void registerBook(@RequestBody AddBookRequestDTO request) {
        public void registerBook() {
        AddBookRequestDTO request = new AddBookRequestDTO();
        request.setAuthorId(21L);
        request.setIsbn("0679735771");
        request.setTitle("American Psycho");
        request.setIssueDate(new DateTime());
        BookInputDTO bookInputDTO = addBookDTOConverter.convert(request);
        bookService.addBook(bookInputDTO);
    }

    @RequestMapping(method = RequestMethod.POST, value="/find")
    public BookResponse find( @RequestBody BookIdRequest request) {
        BookDTO bookDTO = bookService.findBook(request.getBookId());
        return bookResponseConverter.convert(bookDTO);
    }

    @RequestMapping(method = RequestMethod.POST, value="/findIsbn")
    public List<BookResponse> findBooks(@RequestBody BookIsbnRequestDTO request) {
        List<BookDTO> books = bookService.findBooks(request.getIsbn());
        return convert(books);
    }

    @RequestMapping(method = RequestMethod.POST, value="/findTitle")
    public List<BookResponse> findBooksByTitle(@RequestBody BookTitleRequestDTO request) {
        List<BookDTO> books = bookService.findBooksByTitle(request.getTitle());
        return convert(books);
    }

    @RequestMapping(method = RequestMethod.POST, value="/findAuthor")
    public List<BookResponse> findBooksByAuthor(@RequestBody BookAuthorRequestDTO request) {
        List<BookDTO> books = bookService.findBooksByAuthor(request.getAuthorId());
        return convert(books);
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    public void updateBookTitle(@RequestBody BookTitleUpdateRequestDTO request) {
        List<BookDTO> books = bookService.findBooks(request.getIsbn());
        for(BookDTO book: books) {
            bookService.updateBookTitle(book.getId(), request.getTitle());
        }
    }

    private List<BookResponse> convert(List<BookDTO> books) {
        List<BookResponse> responseList = new ArrayList<>(books.size());
        for(BookDTO book: books) {
            BookResponse response = bookResponseConverter.convert(book);
            responseList.add(response);
        }
        return responseList;
    }

 }
