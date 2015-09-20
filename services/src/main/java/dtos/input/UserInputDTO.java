package dtos.input;


import dtos.ServiceDTO;
import org.joda.time.DateTime;

public class UserInputDTO implements ServiceDTO {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private DateTime birth;
    private boolean isAdmin;

    public UserInputDTO(String firstName, String lastName, String password, String email, DateTime birth, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.birth = birth;
        this.isAdmin = isAdmin;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public DateTime getBirth() {
        return birth;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
