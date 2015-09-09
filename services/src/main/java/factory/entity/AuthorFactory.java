package factory.entity;


import domain.Author;
import domain.Book;
import dtos.input.AuthorInputDTO;

import java.util.ArrayList;

public class AuthorFactory {

    public static Author createNew(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBooks(new ArrayList<Book>());
        return author;
    }
}
