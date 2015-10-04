package com.pediritti.library.business.author.query;

import com.pediritti.library.domain.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AuthorsAllQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Author> findAll() {
        Query query = entityManager.createQuery("select a from author a");
        final List<Author> resultList = query.getResultList();
        return resultList;
    }
}
