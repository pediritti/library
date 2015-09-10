package query;

import domain.Borrowed;
import domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BorrowingsQuery extends AbstractParamQuery<Borrowed, User> implements ResultListQuery<Borrowed, User> {

    private static final String FIELD_NAME = "user";

    public BorrowingsQuery() {
        init(Borrowed.class, User.class, FIELD_NAME);
    }

    @Override
    public List<Borrowed> find(User user) {
        typedQuery.setParameter(parameter, user);
        return typedQuery.getResultList();
    }
}
