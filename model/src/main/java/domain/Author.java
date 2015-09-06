package domain;

import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Author extends Person {

    @Id
    private long id;
    private String email;
    private DateTime birth;

    public Author() {

    }

    public Author(String email, String firstName, String lastName, DateTime birth) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateTime getBirth() {
        return birth;
    }

    public void setBirth(DateTime birth) {
        this.birth = birth;
    }
}
