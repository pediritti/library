package com.pediritti.library.service;

import com.pediritti.library.business.ToDtoMapper;
import com.pediritti.library.business.author.command.AuthorCommand;
import com.pediritti.library.business.book.BookFactory;
import com.pediritti.library.business.book.command.BookCommand;
import com.pediritti.library.business.book.command.BookRegistrationCommand;
import com.pediritti.library.business.book.query.BooksByAuthorQuery;
import com.pediritti.library.business.book.query.BooksByIsbnQuery;
import com.pediritti.library.business.book.query.BooksByTitleQuery;
import com.pediritti.library.domain.Author;
import com.pediritti.library.domain.Book;
import com.pediritti.library.dtos.input.BookInputDTO;
import com.pediritti.library.dtos.result.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private AuthorCommand authorCommand;
    @Autowired
    private BookCommand bookCommand;
    @Autowired
    private BookRegistrationCommand bookRegistrationCommand;
    @Autowired
    private BooksByIsbnQuery booksByIsbnQuery;
    @Autowired
    private BooksByAuthorQuery booksByAuthorQuery;
    @Autowired
    private BooksByTitleQuery booksByTitleQuery;
    @Autowired
    private ToDtoMapper<Book, BookDTO> bookToDtoMapper;

    @Transactional
    public BookDTO findBook(long id) {
        Book book = getBook(id);
        return bookToDtoMapper.map(book);
    }

    @Transactional
    public List<BookDTO> findBooks(String isbn) {
        List<Book> books = booksByIsbnQuery.find(isbn);
        return bookToDtoMapper.map(books);
    }

    @Transactional
    public List<BookDTO> findBooksByAuthor(long authorId) {
        Optional<Author> authorOptional = authorCommand.find(authorId);
        if(authorOptional.isPresent()) {
            Author author = authorOptional.get();
            List<Book> books = booksByAuthorQuery.find(author);
            return bookToDtoMapper.map(books);
        } else {
            throw new NoSuchElementException("Author not found with id: " + authorId);
        }
    }

    @Transactional
    public List<BookDTO> findBooksByTitle(String title) {
        List<Book> books = booksByTitleQuery.find(title);
        return bookToDtoMapper.map(books);
    }

    @Transactional
    public BookDTO addBook(BookInputDTO bookDto) {
        Author author = getAuthor(bookDto.getAuthorId());
        Book book = BookFactory.createNew(author, bookDto.getIsbn(), bookDto.getTitle(), bookDto.getIssueDate());
        bookRegistrationCommand.create(book);
        return bookToDtoMapper.map(book);
    }

    @Transactional
    public void updateBookTitle(long id, String title) {
        Book book = getBook(id);
        book.setTitle(title);
    }

    private Book getBook(long bookId) {
        Optional<Book> bookOptional = bookCommand.find(bookId);
        if (bookOptional.isPresent()) {
            return bookOptional.get();
        } else {
            throw new NoSuchElementException("Book not found with id: " + bookId);
        }
    }

    private Author getAuthor(long authorId) {
        Optional<Author> authorOptional = authorCommand.find(authorId);
        if (authorOptional.isPresent()) {
            return authorOptional.get();
        } else {
            throw new NoSuchElementException("Author not found with id: " + authorId);
        }
    }

}
