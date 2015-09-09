package dtos.input;


import dtos.DTO;

public class AuthorInputDTO implements DTO {

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
