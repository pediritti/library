package com.pediritti.library.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@SequenceGenerator(name="seq", initialValue=1, allocationSize=1, sequenceName="book_seq")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq")
    @Column(name="book_id")
    private long id;
    @ManyToOne
    private Author author;
    private String isbn;
    private String title;
    private Date issueDate;

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}
