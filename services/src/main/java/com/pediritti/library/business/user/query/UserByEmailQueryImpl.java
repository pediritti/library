package com.pediritti.library.business.user.query;


import com.pediritti.library.domain.Person;

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
public class UserByEmailQueryImpl implements UserByEmailQuery {

    private static final String FIELD_NAME = "email";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Optional<Person> find(String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        ParameterExpression<String> parameter = criteriaBuilder.parameter(String.class);

        CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
        Root<Person> table = query.from(Person.class);
        query.select(table).where(criteriaBuilder.equal(table.get(FIELD_NAME), parameter));

        TypedQuery<Person> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(parameter, email);

        Person person;
        try {
            person = typedQuery.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            return Optional.empty();
        }
        return Optional.of(person);
    }
}
