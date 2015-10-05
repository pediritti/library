package com.pediritti.library.business.borrow.query;

import com.pediritti.library.domain.Book;
import com.pediritti.library.domain.Borrowed;
import com.pediritti.library.domain.Borrowed_;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class BorrowingByBookQueryImpl implements BorrowingByBookQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Optional<Borrowed> find(Book book) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        ParameterExpression<Book> parameter = criteriaBuilder.parameter(Book.class);

        CriteriaQuery<Borrowed> query = criteriaBuilder.createQuery(Borrowed.class);
        Root<Borrowed> table = query.from(Borrowed.class);
        query.select(table).where(criteriaBuilder.equal(table.get(Borrowed_.book), parameter));

        TypedQuery<Borrowed> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(parameter, book);

        Borrowed borrowed;
        try {
            borrowed = typedQuery.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            return Optional.empty();
        }
        return Optional.of(borrowed);
    }
}
