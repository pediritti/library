package com.pediritti.library.business.borrow.query;

import com.pediritti.library.domain.Borrowed;
import com.pediritti.library.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BorrowingQuery {

    private static final String FIELD_NAME = "user";
    @PersistenceContext
    private EntityManager entityManager;

    public List<Borrowed> find(User user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        ParameterExpression<User> parameter = criteriaBuilder.parameter(User.class);

        CriteriaQuery<Borrowed> query = criteriaBuilder.createQuery(Borrowed.class);
        Root<Borrowed> table = query.from(Borrowed.class);
        query.select(table).where(criteriaBuilder.equal(table.get(FIELD_NAME), parameter));

        TypedQuery<Borrowed> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(parameter, user);
        return typedQuery.getResultList();
    }
}
