import dtos.*;
import dtos.input.AuthorInputDTO;
import dtos.input.BookInputDTO;
import dtos.input.UserInputDTO;

import java.util.List;

public interface ServiceFacade {

    BookDTO findBook(long id);
    List<BookDTO> findBooks(String isbn);
    List<BookDTO> findBooksByAuthor(long authorId);
    List<BookDTO> findBooksByTitle(String title);
    void addBook(BookInputDTO bookInputDTO);
    boolean updateBookTitle(long id, String title);
    UserDTO findUser(long id);
    UserDTO findUserByEmail(String email);
    void registerUser(UserInputDTO userInputDTO);
    List<AuthorDTO> getAuthors();
    AuthorDTO findAuthor(long id);
    List<AuthorDTO> findAuthorByName(String name);
    void registerAuthor(AuthorInputDTO authorInputDTO);
    List<BorrowingDTO> listActiveBorrowings(long userId);
    boolean borrowBook(long userId, long bookId);
    boolean returnBook(long userId, long bookId);
}
