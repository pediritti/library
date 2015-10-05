package com.pediritti.library.business.author.query;

import com.pediritti.library.domain.Author;
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
public class AuthorByNameQueryImpl implements AuthorByNameQuery {

    private static final String FIELD_NAME = "name";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<Author> find(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        ParameterExpression<String> parameter = criteriaBuilder.parameter(String.class);

        CriteriaQuery<Author> query = criteriaBuilder.createQuery(Author.class);
        Root<Author> table = query.from(Author.class);
        query.select(table).where(criteriaBuilder.equal(table.get(FIELD_NAME), parameter));

        TypedQuery<Author> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(parameter, name);
        return typedQuery.getResultList();
    }

}
