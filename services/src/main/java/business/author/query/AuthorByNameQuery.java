package business.author.query;

import business.AbstractParamQuery;
import domain.Author;
import business.ResultListQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
