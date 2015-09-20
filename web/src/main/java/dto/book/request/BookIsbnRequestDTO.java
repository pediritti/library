package dto.book.request;


import dto.RequestDTO;

public class BookIsbnRequestDTO implements RequestDTO {

    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
