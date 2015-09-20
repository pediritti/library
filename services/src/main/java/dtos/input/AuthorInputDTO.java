package dtos.input;


import dtos.ServiceDTO;

public class AuthorInputDTO implements ServiceDTO {

    private String firstName;
    private String lastName;

    public AuthorInputDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
