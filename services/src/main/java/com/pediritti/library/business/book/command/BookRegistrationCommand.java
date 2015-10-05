package com.pediritti.library.business.book.command;


import com.pediritti.library.domain.Book;
import org.springframework.transaction.annotation.Transactional;

public interface BookRegistrationCommand {

    void create(Book book);
}
