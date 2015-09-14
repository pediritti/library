package dtos.result;

import org.joda.time.DateTime;

public class BorrowingDTO {

    private long userId;
    private long bookId;
    private String title;
    private String author;
    private DateTime borrowDate;
    private DateTime expectedReturnDate;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
