package project.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="faculties")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="plan_number")
    private int planNumber;

    public Faculty() {
    }

    public Faculty(Integer id, String name, int planNumber) {
        this.id = id;
        this.name = name;
        this.planNumber = planNumber;
    }

    public Faculty(String name, int planNumber) {
        this.name = name;
        this.planNumber = planNumber;
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

    public void setName(String name) {
        this.name = name;
    }

    public int getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(int planNumber) {
        this.planNumber = planNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return planNumber == faculty.planNumber && Objects.equals(id, faculty.id) && Objects.equals(name, faculty.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, planNumber);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", planNumber=" + planNumber +
                '}';
    }
}
