package business.author.command;


import domain.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class AuthorCommand {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Author> find(long id) {
        return Optional.of(entityManager.find(Author.class, id));
    }

}
