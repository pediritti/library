package business;


import domain.Author;
import domain.Book;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ToDtoMapper<E, D> {

    public interface Mapper<E, D> {
        D map(E entity);
    }

    public Mapper<E, D> mapper;

    public abstract void setMapper();

    public ToDtoMapper() {
        setMapper();
    }

    public D map(E entity) {
        return mapper.map(entity);
    }

    public List<D> map(List<E> entities) {
        return entities
                .stream()
                .map(entity -> mapper.map(entity))
                .collect(Collectors.toList());
    }

    protected String getAuthorName(Book book) {
        Author author = book.getAuthor();
        return author.getFirstName() + " " + author.getLastName();
    }

}
