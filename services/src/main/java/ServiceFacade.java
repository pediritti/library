import dtos.*;
import org.joda.time.DateTime;

import java.util.List;

public interface ServiceFacade {

    BookDTO findBook(long id);
    List<BookDTO> findBooks(String isbn);
    List<BookDTO> findBooksByAuthor(long authorId);
    List<BookDTO> findBooksByTitle(String title);
    BookDTO addBook(String isbn, String title, long authorId, DateTime issueDate);
    boolean updateBook(long id, String isbn, String title, long authorId, DateTime issueDate);
    ReaderDTO findReader(long id);
    ReaderDTO findReaderByName(String name);
    ReaderDTO registerReader(String email, String firstName, String lastName, DateTime birth);
    boolean updateReader(long id, String email, String firstName, String lastName, DateTime birth);
    List<AuthorDTO> getAuthors();
    AuthorDTO findAuthorByName(String name);
    AuthorDTO registerAuthor(String firstName, String lastName);
    List<BorrowingDTO> listActiveBorrowings(long readerId);
    boolean borrowBook(long readerId, long bookId);
    boolean returnBook(long bookId);
}
