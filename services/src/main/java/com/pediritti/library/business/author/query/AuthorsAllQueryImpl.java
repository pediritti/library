package com.pediritti.library.business.author.query;

import com.pediritti.library.domain.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AuthorsAllQueryImpl implements AuthorsAllQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<Author> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Author> query = criteriaBuilder.createQuery(Author.class);
        Root<Author> table = query.from(Author.class);
        query.select(table);

        TypedQuery<Author> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}
