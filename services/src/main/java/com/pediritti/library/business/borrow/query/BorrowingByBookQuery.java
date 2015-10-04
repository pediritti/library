package com.pediritti.library.business.borrow.query;

import com.pediritti.library.domain.Book;
import com.pediritti.library.domain.Borrowed;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class BorrowingByBookQuery {

    private static final String FIELD_NAME = "book";

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Borrowed> find(Book book) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        ParameterExpression<Book> parameter = criteriaBuilder.parameter(Book.class);

        CriteriaQuery<Borrowed> query = criteriaBuilder.createQuery(Borrowed.class);
        Root<Borrowed> table = query.from(Borrowed.class);
        query.select(table).where(criteriaBuilder.equal(table.get(FIELD_NAME), parameter));

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
