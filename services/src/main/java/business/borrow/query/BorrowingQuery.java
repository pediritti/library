package business.borrow.query;

import business.ResultListQuery;
import domain.Borrowed;
import domain.User;
import business.AbstractParamQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BorrowingQuery extends AbstractParamQuery<Borrowed, User> implements ResultListQuery<Borrowed, User> {

    private static final String FIELD_NAME = "user";

    public BorrowingQuery() {
        init(Borrowed.class, User.class, FIELD_NAME);
    }

    @Override
    public List<Borrowed> find(User user) {
        typedQuery.setParameter(parameter, user);
        return typedQuery.getResultList();
    }
}
