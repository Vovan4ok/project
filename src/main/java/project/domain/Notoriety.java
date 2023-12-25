package project.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="notoriety")
public class Notoriety {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    @Column(name="faculty_id")
    private Integer facultyId;
    @Column(name="applicant_id")
    private Integer applicantId;
    @Column(name="mark")
    private float mark;

    public Notoriety() {
    }

    public Notoriety(Integer id, Integer facultyId, Integer applicantId, float mark) {
        this.id = id;
        this.facultyId = facultyId;
        this.applicantId = applicantId;
        this.mark = mark;
    }

    public Notoriety(Integer facultyId, Integer applicantId, float mark) {
        this.facultyId = facultyId;
        this.applicantId = applicantId;
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notoriety notoriety = (Notoriety) o;
        return Float.compare(mark, notoriety.mark) == 0 && Objects.equals(id, notoriety.id) && Objects.equals(facultyId, notoriety.facultyId) && Objects.equals(applicantId, notoriety.applicantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, facultyId, applicantId, mark);
    }

    @Override
    public String toString() {
        return "Notoriety{" +
                "id=" + id +
                ", facultyId=" + facultyId +
                ", applicantId=" + applicantId +
                ", mark=" + mark +
                '}';
    }
}
