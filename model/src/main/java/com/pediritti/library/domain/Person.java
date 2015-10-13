package com.pediritti.library.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@SequenceGenerator(name="seq", initialValue=1, allocationSize=1, sequenceName="person_seq")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq")
    @Column(name="PERSON_ID")
    private long id;
    @Column(name="FIRSTNAME")
    private String firstName;
    @Column(name="LASTNAME")
    private String lastName;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="EMAIL")
    private String email;
    @Column(name="BIRTH")
    private Date birth;

    public Person() {

    }

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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
