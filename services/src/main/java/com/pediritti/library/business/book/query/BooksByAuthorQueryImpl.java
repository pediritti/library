package com.pediritti.library.business.book.query;

import com.pediritti.library.domain.Author;
import com.pediritti.library.domain.Book;
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
public class BooksByAuthorQueryImpl implements BooksByAuthorQuery {

    private static final String FIELD_NAME = "author";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<Book> find(Author author) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        ParameterExpression<Author> parameter = criteriaBuilder.parameter(Author.class);

        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> table = query.from(Book.class);
        query.select(table).where(criteriaBuilder.equal(table.get(FIELD_NAME), parameter));

        TypedQuery<Book> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(parameter, author);
        return typedQuery.getResultList();
    }
}
