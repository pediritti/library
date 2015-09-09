package query;

import domain.Borrowed;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class BorrowingsQuery {

    @PersistenceContext
    private EntityManager entityManager;
    private Query findByUser;

    public BorrowingsQuery() {
        findByUser = entityManager.createQuery(
                "TBD"
        );
    }

    public List<Borrowed> find(long userId) {
        findByUser.setParameter("userId", userId);
        List<Borrowed> borrowings = findByUser.getResultList();
        return borrowings;
    }
}
