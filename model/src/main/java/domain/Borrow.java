package domain;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Borrow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="borrow_id")
    private long id;
    private User reader;
    private Book book;
    private DateTime borrowDate;
    private DateTime returnDate;
    private DateTime expectedReturnDate;

    public Borrow() {

    }

    public Borrow(User reader, Book book, DateTime borrowDate, DateTime returnDate, DateTime expectedReturnDate) {
        this.reader = reader;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.expectedReturnDate = expectedReturnDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getReader() {
        return reader;
    }

    public void setReader(User reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public DateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(DateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public DateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(DateTime returnDate) {
        this.returnDate = returnDate;
    }

    public DateTime getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(DateTime expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }
}
