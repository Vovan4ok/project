package project.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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

    @Column(name="patronimic")
    private String patronimic;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="marks_url")
    private String marksUrl;

    public User(User user) {
        this.id = user.id;
        this.email = user.email;
        this.password = user.password;
        this.name = user.name;
        this.surname = user.surname;
        this.patronimic = user.patronimic;
        this.role = user.role;
    }

    public User(String email, String password, String name, String surname, String patronimic, Role role, String imageUrl, String marksUrl) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.patronimic = patronimic;
        this.role = role;
        this.imageUrl = imageUrl;
        this.marksUrl = marksUrl;
    }
}
