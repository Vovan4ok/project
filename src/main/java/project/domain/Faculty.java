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

    public Faculty(String name, Short planNumber) {
        this.name = name;
        this.planNumber = planNumber;
    }
}
