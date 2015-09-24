package com.pediritti.library.business.book;


import com.pediritti.library.domain.Author;
import com.pediritti.library.domain.Book;
import org.joda.time.DateTime;
import java.util.Date;

public class BookFactory  {

    public static Book createNew(Author author, String isbn, String title, DateTime issueDate) {
        Book book = new Book();
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setIssueDate(new Date(issueDate.getMillis()));
        return book;
    }

}
