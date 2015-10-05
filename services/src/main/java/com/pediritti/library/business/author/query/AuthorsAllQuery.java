package com.pediritti.library.business.author.query;


import com.pediritti.library.domain.Author;

import java.util.List;

public interface AuthorsAllQuery {

    List<Author> findAll();
}
