package factory.entity;


import domain.Admin;
import domain.Person;
import domain.User;
import org.joda.time.DateTime;

import java.util.Date;


public class PersonFactory {

    public static Person createUser(String firstName, String lastName, String password,
                                    String email, DateTime birth, boolean isAdmin) {
        Person person = getPerson(isAdmin);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPassword(password);
        person.setEmail(email);
        person.setBirth(new Date(birth.getMillis()));
        return person;
    }

    private static Person getPerson(boolean isAdmin) {
        if(isAdmin) {
            return new Admin();
        } else {
            return new User();
        }
    }


}

