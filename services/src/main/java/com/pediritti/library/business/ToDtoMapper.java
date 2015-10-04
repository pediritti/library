package com.pediritti.library.business;


import com.pediritti.library.domain.Author;
import com.pediritti.library.domain.Book;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class ToDtoMapper<E, D> {

    public Function<E, D> mapper;

    public abstract void setMapper();

    public ToDtoMapper() {
        setMapper();
    }

    public D map(E entity) {
        return mapper.apply(entity);
    }

    public List<D> map(List<E> entities) {
        return entities
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    protected String getAuthorName(Book book) {
        Author author = book.getAuthor();
        return author.getFirstName() + " " + author.getLastName();
    }

}
