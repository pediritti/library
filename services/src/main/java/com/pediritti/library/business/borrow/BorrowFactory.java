package com.pediritti.library.business.borrow;


import com.pediritti.library.domain.*;

import java.util.Date;

public class BorrowFactory {

    public static Borrowed createBorrowed(Borrower borrower, Book book) {
        Borrowed borrowed = new Borrowed();
        borrowed.setBorrower(borrower);
        borrowed.setBook(book);
        borrowed.setBorrowDate(new Date());
        // TODO: implement logic to set expected return date.
        borrowed.setExpectedReturnDate(new Date());
        return borrowed;
    }

    public static Returned createReturned(Borrowed borrowed) {
        Returned returned = new Returned();
        returned.setBorrower(borrowed.getBorrower());
        returned.setBook(borrowed.getBook());
        returned.setBorrowDate(borrowed.getBorrowDate());
        returned.setExpectedReturnDate(borrowed.getExpectedReturnDate());
        returned.setReturnDate(new Date());
        return returned;
    }
}
