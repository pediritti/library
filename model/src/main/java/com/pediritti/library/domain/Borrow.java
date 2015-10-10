package com.pediritti.library.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="seq", initialValue=1, allocationSize=1, sequenceName="borrow_seq")
public class Borrow implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq")
    private long id;
    @ManyToOne
    private Borrower borrower;
    @ManyToOne
    private Book book;
    private Date borrowDate;
    private Date expectedReturnDate;

    public Borrow() {

    }

    public Borrow(long id, Borrower borrower, Book book, Date borrowDate, Date expectedReturnDate) {
        this.id = id;
        this.borrower = borrower;
        this.book = book;
        this.borrowDate = borrowDate;
        this.expectedReturnDate = expectedReturnDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }
}
