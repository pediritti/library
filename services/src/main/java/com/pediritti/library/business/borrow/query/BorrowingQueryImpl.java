package com.pediritti.library.business.borrow.query;

import com.pediritti.library.domain.Borrowed;
import com.pediritti.library.domain.Borrowed_;
import com.pediritti.library.domain.Borrower;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class BorrowingQueryImpl implements BorrowingQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Borrowed> find(Borrower user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        ParameterExpression<Borrower> parameter = criteriaBuilder.parameter(Borrower.class);

        CriteriaQuery<Borrowed> query = criteriaBuilder.createQuery(Borrowed.class);
        Root<Borrowed> table = query.from(Borrowed.class);
        query.select(table).where(criteriaBuilder.equal(table.get(Borrowed_.borrower), parameter));

        TypedQuery<Borrowed> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(parameter, user);
        return typedQuery.getResultList();
    }
}
