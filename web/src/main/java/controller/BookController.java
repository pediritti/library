package controller;

import converter.book.AddBookDTOConverter;
import dto.book.request.*;
import dtos.input.BookInputDTO;
import dtos.result.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.BookService;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AddBookDTOConverter addBookDTOConverter;

    public void registerBook(AddBookRequestDTO request) {

        BookInputDTO bookInputDTO = addBookDTOConverter.convert(request);
        bookService.addBook(bookInputDTO);

    }

    public void findBooks(BookIsbnRequestDTO request) {
        bookService.findBooks(request.getIsbn());
    }

    public void findBooksByTitle(BookTitleRequestDTO request) {
        bookService.findBooksByTitle(request.getTitle());
    }

    public void findBooksByAuthor(BookAuthorRequestDTO request) {
        bookService.findBooksByAuthor(request.getAuthorId());
    }

    public void updateBookTitle(BookTitleUpdateRequestDTO request) {
        List<BookDTO> books = bookService.findBooks(request.getIsbn());
        for(BookDTO book: books) {
            bookService.updateBookTitle(book.getId(), request.getTitle());
        }
    }
 }
