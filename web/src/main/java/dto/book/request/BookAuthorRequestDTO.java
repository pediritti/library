package dto.book.request;


import dto.RequestDTO;

public class BookAuthorRequestDTO implements RequestDTO {

    private long authorId;

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
}
