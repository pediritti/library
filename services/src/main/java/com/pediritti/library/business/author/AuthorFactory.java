package com.pediritti.library.business.author;


import com.pediritti.library.domain.Author;
import com.pediritti.library.domain.Book;

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
