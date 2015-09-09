import command.*;
import query.*;
import domain.*;
import dtos.*;
import dtos.input.*;
import factory.entity.*;
import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class ServiceFacadeImpl implements ServiceFacade {

    @Autowired
    private AuthorRegistrationCommand authorRegistrationCommand;
    @Autowired
    private BookRegistrationCommand bookRegistrationCommand;
    @Autowired
    private UserRegistrationCommand userRegistrationCommand;
    @Autowired
    private SetBookTitleCommand setBookTitleCommand;
    @Autowired
    private BorrowCommand borrowCommand;
    @Autowired
    private ReturnCommand returnCommand;

    @Autowired
    private BookQuery bookQuery;
    @Autowired
    private BooksByIsbnQuery booksByIsbnQuery;
    @Autowired
    private BooksByAuthorQuery booksByAuthorQuery;
    @Autowired
    private BooksByTitleQuery booksByTitleQuery;
    @Autowired
    private AuthorQuery authorQuery;
    @Autowired
    private AuthorByNameQuery authorByNameQuery;
    @Autowired
    private AuthorsAllQuery authorsAllQuery;
    @Autowired
    private UserQuery userQuery;
    @Autowired
    private UserByEmailQuery userByEmailQuery;
    @Autowired
    private BorrowingsQuery borrowingsQuery;
    @Autowired
    private BorrowingByBookQuery borrowingByBookQuery;

    @Autowired
    private BookToDtoMapper bookToDtoMapper;
    @Autowired
    private AuthorToDtoMapper authorToDtoMapper;
    @Autowired
    private UserToDtoMapper userToDtoMapper;
    @Autowired
    private BorrowToDtoMapper borrowToDtoMapper;

    @Override
    public BookDTO findBook(long id) {
        Book book = bookQuery.find(id);
        return bookToDtoMapper.map(book);
    }

    @Override
    public List<BookDTO> findBooks(String isbn) {
        List<Book> books = booksByIsbnQuery.execute(isbn);
        return bookToDtoMapper.map(books);
    }

    @Override
    public List<BookDTO> findBooksByAuthor(long authorId) {
        List<Book> books = booksByAuthorQuery.execute(authorId);
        return bookToDtoMapper.map(books);
    }

    @Override
    public List<BookDTO> findBooksByTitle(String title) {
        List<Book> books = booksByTitleQuery.execute(title);
        return bookToDtoMapper.map(books);
    }

    @Override
    public void addBook(BookInputDTO bookDto) {
        Author author = authorQuery.find(bookDto.getAuthorId());
        Book book = BookFactory.createNew(author, bookDto.getIsbn(), bookDto.getTitle(), bookDto.getIssueDate());
        bookRegistrationCommand.create(book);
    }

    @Override
    public boolean updateBookTitle(long id, String title) {
        Book book = bookQuery.find(id);
        book.setTitle(title);
        return setBookTitleCommand.update(book);

    }

    @Override
    public UserDTO findUser(long id) {
        Person person = userQuery.find(id);
        return userToDtoMapper.map(person);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        Person person = userByEmailQuery.find(email);
        return userToDtoMapper.map(person);
    }

    @Override
    public void registerUser(UserInputDTO inputDTO) {
        Person person = PersonFactory.createUser(inputDTO.getFirstName(), inputDTO.getLastName(),
                inputDTO.getPassword(), inputDTO.getEmail(), inputDTO.getBirth(), inputDTO.isAdmin());
        userRegistrationCommand.create(person);
    }

    @Override
    public List<AuthorDTO> getAuthors() {
        List<Author> authors = authorsAllQuery.findAll();
        return authorToDtoMapper.map(authors);
    }

    @Override
    public AuthorDTO findAuthor(long id) {
        Author author = authorQuery.find(id);
        return authorToDtoMapper.map(author);
    }

    @Override
    public List<AuthorDTO> findAuthorByName(String name) {
        List<Author> authors = authorByNameQuery.find(name);
        return authorToDtoMapper.map(authors);
    }

    @Override
    public void registerAuthor(AuthorInputDTO inputDTO) {
        Author author = AuthorFactory.createNew(inputDTO.getFirstName(), inputDTO.getLastName());
        authorRegistrationCommand.create(author);
    }

    @Override
    public List<BorrowingDTO> listActiveBorrowings(long userId) {
        List<Borrowed> borrowed = borrowingsQuery.find(userId);
        return borrowToDtoMapper.map(borrowed);
    }

    @Override
    public boolean borrowBook(long userId, long bookId) {
        User user = (User)userQuery.find(userId);
        Book book = bookQuery.find(bookId);
        Borrowed borrowed = BorrowFactory.create(user, book);
        borrowCommand.save(borrowed);
        return true;
    }

    @Override
    public boolean returnBook(long userId, long bookId) {
        Borrowed borrowed = (Borrowed)borrowingByBookQuery.find(bookId);
        Returned returned = BorrowFactory.create(borrowed);
        returnCommand.setReturned(borrowed, returned);
        return true;
    }

}
