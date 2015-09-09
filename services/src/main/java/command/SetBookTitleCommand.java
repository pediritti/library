package command;


import domain.Book;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class SetBookTitleCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean update(Book book) {
        Book merge = entityManager.merge(book);
        return true;
    }

}
