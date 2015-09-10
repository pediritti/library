package query;

import domain.Author;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorByNameQuery extends AbstractParamQuery<Author, String> implements ResultListQuery<Author, String> {

    private static final String FIELD_NAME = "name";

    public AuthorByNameQuery() {
        init(Author.class, String.class, FIELD_NAME);
    }

    @Override
    public List<Author> find(String name) {
        typedQuery.setParameter(parameter, name);
        return typedQuery.getResultList();
    }

}
