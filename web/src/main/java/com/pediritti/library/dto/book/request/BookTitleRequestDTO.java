package com.pediritti.library.dto.book.request;

import com.pediritti.library.dto.RequestDTO;


public class BookTitleRequestDTO implements RequestDTO {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
