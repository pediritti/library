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
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorByNameQueryImpl implements AuthorByNameQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<Author> find(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        ParameterExpression<String> parameter = criteriaBuilder.parameter(String.class);

        CriteriaQuery<Author> query = criteriaBuilder.createQuery(Author.class);
        Root<Author> table = query.from(Author.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(criteriaBuilder.equal(table.get(Author_.firstName), parameter));
        predicates.add(criteriaBuilder.equal(table.get(Author_.lastName), parameter));

        query.select(table);
        query.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Author> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(parameter, name);
        return typedQuery.getResultList();
    }

}
