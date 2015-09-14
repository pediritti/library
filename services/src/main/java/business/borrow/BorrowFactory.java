package business.borrow;


import domain.*;

import java.util.Date;

public class BorrowFactory {

    public static Borrowed createBorrowed(User user, Book book) {
        Borrowed borrowed = new Borrowed();
        borrowed.setUser(user);
        borrowed.setBook(book);
        borrowed.setBorrowDate(new Date());
        // TODO: implement logic to set expected return date.
        borrowed.setExpectedReturnDate(new Date());
        return borrowed;
    }

    public static Returned createReturned(Borrowed borrowed) {
        Returned returned = new Returned();
        returned.setUser(borrowed.getUser());
        returned.setBook(borrowed.getBook());
        returned.setBorrowDate(borrowed.getBorrowDate());
        returned.setExpectedReturnDate(borrowed.getExpectedReturnDate());
        returned.setReturnDate(new Date());
        return returned;
    }
}
