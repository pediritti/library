package dto.book.request;

import dto.RequestDTO;


public class BookTitleRequestDTO implements RequestDTO {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
