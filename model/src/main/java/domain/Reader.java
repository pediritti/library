package domain;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reader extends Person {

    @Id
    private long id;

    public Reader() {

    }

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
