package com.pediritti.library.business.author.query;

import com.pediritti.library.domain.Author;
import com.pediritti.library.domain.Author_;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class AuthorByNameQueryImpl implements AuthorByNameQuery {

    private static final String WILDCARD = "%";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<Author> find(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        ParameterExpression<String> parameter = criteriaBuilder.parameter(String.class);

        CriteriaQuery<Author> query = criteriaBuilder.createQuery(Author.class);
        Root<Author> authorTable = query.from(Author.class);

        query.select(authorTable);
        query.where(criteriaBuilder.or(
                criteriaBuilder.like(authorTable.get(Author_.firstName), parameter),
                criteriaBuilder.like(authorTable.get(Author_.lastName), parameter))
        );

        TypedQuery<Author> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(parameter, WILDCARD + name + WILDCARD);
        return typedQuery.getResultList();
    }

}
