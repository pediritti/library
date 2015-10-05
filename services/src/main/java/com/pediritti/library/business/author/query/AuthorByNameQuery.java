package com.pediritti.library.business.author.query;


import com.pediritti.library.domain.Author;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthorByNameQuery {

    List<Author> find(String name);
}
