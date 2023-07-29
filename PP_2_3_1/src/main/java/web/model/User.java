package web.model;


import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "The name field can contain only letters")
    @Size(min = 2, max = 45, message = "Name should be between 2 and 45 characters")
    private String name;

    @Column
    @NotEmpty(message = "Surname should not be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "The surname field can contain only letters")
    @Size(min = 2, max = 45, message = "SurName should be between 2 and 45 characters")
    private String surname;

    @Column
    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    public User() {
    }

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
