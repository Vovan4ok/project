package project.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="short_name")
    private String shortName;

    @ManyToOne
    @JoinColumn(name="faculty_id")
    private Faculty faculty;

    @Column(name="description")
    private String description;

    @Column(name="head_of_department")
    private String headOfDepartment;

    @Column(name="established_year")
    private Short establishedYear;

    @Column(name="number_of_professors")
    private Short numberOfProfessors;

    @Column(name="number_of_assistants")
    private Short numberOfAssistants;

    public Department(String name, String shortName, Faculty faculty, String description, String headOfDepartment, Short establishedYear, Short numberOfProfessors, Short numberOfAssistants) {
        this.name = name;
        this.shortName = shortName;
        this.faculty = faculty;
        this.description = description;
        this.headOfDepartment = headOfDepartment;
        this.establishedYear = establishedYear;
        this.numberOfProfessors = numberOfProfessors;
        this.numberOfAssistants = numberOfAssistants;
    }
}
