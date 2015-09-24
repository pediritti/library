package com.pediritti.library.dto.book.request;


import com.pediritti.library.dto.RequestDTO;

public class BookAuthorRequestDTO implements RequestDTO {

    private long authorId;

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
}
