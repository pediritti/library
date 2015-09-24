package com.pediritti.library.dtos.input;


import com.pediritti.library.dtos.ServiceDTO;
import org.joda.time.DateTime;

public class BookInputDTO implements ServiceDTO {

    private long authorId;
    private String isbn;
    private String title;
    private DateTime issueDate;

    public BookInputDTO(long authorId, String isbn, String title, DateTime issueDate) {
        this.authorId = authorId;
        this.isbn = isbn;
        this.title = title;
        this.issueDate = issueDate;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public DateTime getIssueDate() {
        return issueDate;
    }
}
