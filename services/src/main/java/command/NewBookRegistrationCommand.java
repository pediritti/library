package command;

import domain.Author;
import domain.Book;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Component
public class NewBookRegistrationCommand {

    @PersistenceContext
    EntityManager entityManager;

    public Book execute(String isbn, String title, long authorId, DateTime issueDate) {
        Book book = new Book(isbn, new Author(), title, issueDate, false);
        entityManager.persist(book);
        return book;
    }

}
