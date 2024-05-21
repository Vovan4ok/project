package project.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="faculties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Short id;

    @Column(name="name")
    private String name;

    @Column(name="plan_number")
    private Short planNumber;

    @Column(name="head_of_faculty")
    private String headOfFaculty;

    @Column(name="established_year")
    private Integer establishedYear;

    @Column(name="number_of_students")
    private Integer numberOfStudents;

    @Column(name="address")
    private String address;

    @Column(name="website_url")
    private String websiteUrl;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="description")
    private String description;

    public Faculty(String name, Short planNumber, String headOfFaculty, Integer establishedYear, Integer numberOfStudents, String address, String websiteUrl, String email, String phone, String description) {
        this.name = name;
        this.planNumber = planNumber;
        this.headOfFaculty = headOfFaculty;
        this.establishedYear = establishedYear;
        this.numberOfStudents = numberOfStudents;
        this.address = address;
        this.websiteUrl = websiteUrl;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }
}
