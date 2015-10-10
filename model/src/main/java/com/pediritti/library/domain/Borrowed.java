package com.pediritti.library.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Borrowed extends Borrow {

    public Borrowed() {
    }

    public Borrowed(long id, Borrower borrower, Book book, Date borrowDate, Date expectedReturnDate) {
        super(id, borrower, book, borrowDate, expectedReturnDate);
    }
}
