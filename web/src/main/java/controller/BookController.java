package controller;

import converter.book.AddBookDTOConverter;
import dto.book.request.*;
import dtos.input.BookInputDTO;
import dtos.result.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.BookService;

import java.util.List;

@RestController("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AddBookDTOConverter addBookDTOConverter;

    @RequestMapping(method = RequestMethod.PUT, value="/register")
    public void registerBook(AddBookRequestDTO request) {

        BookInputDTO bookInputDTO = addBookDTOConverter.convert(request);
        bookService.addBook(bookInputDTO);

    }

    @RequestMapping(method = RequestMethod.PUT, value="/findIsbn")
    public void findBooks(BookIsbnRequestDTO request) {
        bookService.findBooks(request.getIsbn());
    }

    @RequestMapping(method = RequestMethod.PUT, value="/findTitle")
    public void findBooksByTitle(BookTitleRequestDTO request) {
        bookService.findBooksByTitle(request.getTitle());
    }

    @RequestMapping(method = RequestMethod.PUT, value="/findAuthor")
    public void findBooksByAuthor(BookAuthorRequestDTO request) {
        bookService.findBooksByAuthor(request.getAuthorId());
    }

    @RequestMapping(method = RequestMethod.PUT, value="/update")
    public void updateBookTitle(BookTitleUpdateRequestDTO request) {
        List<BookDTO> books = bookService.findBooks(request.getIsbn());
        for(BookDTO book: books) {
            bookService.updateBookTitle(book.getId(), request.getTitle());
        }
    }
 }
