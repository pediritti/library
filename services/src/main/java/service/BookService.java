package service;

import business.author.command.AuthorCommand;
import business.book.BookFactory;
import business.book.BookToDtoMapper;
import business.book.command.BookCommand;
import business.book.command.BookRegistrationCommand;
import business.book.query.BooksByAuthorQuery;
import business.book.query.BooksByIsbnQuery;
import business.book.query.BooksByTitleQuery;
import domain.Author;
import domain.Book;
import dtos.input.BookInputDTO;
import dtos.result.BookDTO;
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
    private BookToDtoMapper bookToDtoMapper;

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
    public void addBook(BookInputDTO bookDto) {
        Author author = getAuthor(bookDto.getAuthorId());
        Book book = BookFactory.createNew(author, bookDto.getIsbn(), bookDto.getTitle(), bookDto.getIssueDate());
        bookRegistrationCommand.create(book);
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
