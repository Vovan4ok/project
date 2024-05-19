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
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name="image_url")
    private String imageUrl;

    public User(User user) {
        this.id = user.id;
        this.email = user.email;
        this.password = user.password;
        this.name = user.name;
        this.surname = user.surname;
        this.role = user.role;
    }
}
