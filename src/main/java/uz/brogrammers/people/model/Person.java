package uz.brogrammers.people.model;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class Person {
    private Integer id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 20, message = "Name should contain at least 3 and at most 20 characters")
    private String name;
    private String surname;
    private String email;

    public Person() {
    }

    public Person(Integer id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;

        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
