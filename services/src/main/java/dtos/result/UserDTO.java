package dtos.result;


import org.joda.time.DateTime;

public class UserDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private DateTime birth;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
