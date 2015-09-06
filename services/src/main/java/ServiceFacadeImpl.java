import command.NewBookRegistrationCommand;
import domain.Book;
import dtos.AuthorDTO;
import dtos.BookDTO;
import dtos.BorrowingDTO;
import dtos.ReaderDTO;
import mapper.BookToDtoMapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import query.GetBookByIdQuery;

import java.util.List;

public class ServiceFacadeImpl implements ServiceFacade {

    @Autowired
    private NewBookRegistrationCommand newBookRegistrationCommand;
    @Autowired
    private GetBookByIdQuery getBookByIdQuery;
    @Autowired
    private BookToDtoMapper bookToDtoMapper;

    @Override
    public BookDTO findBook(long id) {
        Book book = getBookByIdQuery.execute(id);
        return bookToDtoMapper.map(book);
    }

    @Override
    public List<BookDTO> findBooks(String isbn) {
        return null;
    }

    @Override
    public List<BookDTO> findBooksByAuthor(long authorId) {
        return null;
    }

    @Override
    public List<BookDTO> findBooksByTitle(String title) {
        return null;
    }

    @Override
    public BookDTO addBook(String isbn, String title, long authorId, DateTime issueDate) {
        Book book = newBookRegistrationCommand.execute(isbn, title, authorId, issueDate);
        return bookToDtoMapper.map(book);
    }

    @Override
    public boolean updateBook(long id, String isbn, String title, long authorId, DateTime issueDate) {
        return false;
    }

    @Override
    public ReaderDTO findReader(long id) {
        return null;
    }

    @Override
    public ReaderDTO findReaderByName(String name) {
        return null;
    }

    @Override
    public ReaderDTO registerReader(String email, String firstName, String lastName, DateTime birth) {
        return null;
    }

    @Override
    public boolean updateReader(long id, String email, String firstName, String lastName, DateTime birth) {
        return false;
    }

    @Override
    public List<AuthorDTO> getAuthors() {
        return null;
    }

    @Override
    public AuthorDTO findAuthorByName(String name) {
        return null;
    }

    @Override
    public AuthorDTO registerAuthor(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<BorrowingDTO> listActiveBorrowings(long readerId) {
        return null;
    }

    @Override
    public boolean borrowBook(long readerId, long bookId) {
        return false;
    }

    @Override
    public boolean returnBook(long bookId) {
        return false;
    }
}
