package com.pediritti.library.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Returned extends Borrow {

    private Date returnDate;

    public Returned() {
    }

    public Returned(long id, Borrower borrower, Book book, Date borrowDate, Date expectedReturnDate, Date returnDate) {
        super(id, borrower, book, borrowDate, expectedReturnDate);
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
