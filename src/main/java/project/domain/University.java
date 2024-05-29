package project.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="universities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="short_name")
    private String shortName;

    @Column(name="description")
    private String description;

    @Column(name="established_year")
    private Short establishedYear;

    @Column(name="rector")
    private String rector;

    @Column(name="address")
    private String address;

    @Column(name="website_url")
    private String websiteUrl;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    public University(String name, String shortName, String description, Short establishedYear, String rector, String address, String websiteUrl, String email, String phone) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.establishedYear = establishedYear;
        this.rector = rector;
        this.address = address;
        this.websiteUrl = websiteUrl;
        this.email = email;
        this.phone = phone;
    }
}
