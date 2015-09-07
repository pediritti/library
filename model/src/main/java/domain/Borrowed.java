package domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Borrowed extends Borrow {

    public Borrowed() {
    }

    public Borrowed(long id, User reader, Book book, Date borrowDate, Date expectedReturnDate) {
        super(id, reader, book, borrowDate, expectedReturnDate);
    }
}
