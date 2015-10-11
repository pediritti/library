package com.pediritti.library.dtos.result;

import org.joda.time.DateTime;

public class BorrowingDTO {

    private long borrowerId;
    private long bookId;
    private String title;
    private String author;
    private DateTime borrowDate;
    private DateTime expectedReturnDate;

    public long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(long borrowerId) {
        this.borrowerId = borrowerId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public DateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(DateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public DateTime getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(DateTime expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }
}
