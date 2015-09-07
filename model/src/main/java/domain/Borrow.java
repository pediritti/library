package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private Date borrowDate;
    private Date expectedReturnDate;

    public Borrow() {

    }

    public Borrow(long id, User reader, Book book, Date borrowDate, Date expectedReturnDate) {
        this.id = id;
        this.reader = reader;
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
