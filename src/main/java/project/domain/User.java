package project.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    @Column(name="email", unique = true)
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Lob
    private byte[] image;

    @Lob
    private String encodedImage;

    public User() {
    }

    public User(User user) {
        this.id = user.id;
        this.email = user.email;
        this.password = user.password;
        this.name = user.name;
        this.surname = user.surname;
        this.role = user.role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, surname, role);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                ", image=" + Arrays.toString(image) + '\'' +
                '}';
    }
}
