package project.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    @Column(name="applicant_id")
    private Integer applicantId;
    @Column(name="maths_mark")
    private int mathsMark;
    @Column(name="english_mark")
    private int englishMark;
    @Column(name="physics_mark")
    private int physicsMark;
    @Column(name="certificate_mark")
    private int certificateMark;
    @Column(name="confirmed")
    private int confirmed;

    public Application() {
    }

    public Application(Integer id, Integer applicantId, int mathsMark, int englishMark, int physicsMark, int certificateMark) {
        this.id = id;
        this.applicantId = applicantId;
        this.mathsMark = mathsMark;
        this.englishMark = englishMark;
        this.physicsMark = physicsMark;
        this.certificateMark = certificateMark;
        this.confirmed = 0;
    }

    public Application(Integer applicantId, int mathsMark, int englishMark, int physicsMark, int certificateMark) {
        this.applicantId = applicantId;
        this.mathsMark = mathsMark;
        this.englishMark = englishMark;
        this.physicsMark = physicsMark;
        this.certificateMark = certificateMark;
        this.confirmed = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public int getMathsMark() {
        return mathsMark;
    }

    public void setMathsMark(int mathsMark) {
        this.mathsMark = mathsMark;
    }

    public int getEnglishMark() {
        return englishMark;
    }

    public void setEnglishMark(int englishMark) {
        this.englishMark = englishMark;
    }

    public int getPhysicsMark() {
        return physicsMark;
    }

    public void setPhysicsMark(int physicsMark) {
        this.physicsMark = physicsMark;
    }

    public int getCertificateMark() {
        return certificateMark;
    }

    public void setCertificateMark(int certificateMark) {
        this.certificateMark = certificateMark;
    }


    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return mathsMark == that.mathsMark && englishMark == that.englishMark && physicsMark == that.physicsMark && certificateMark == that.certificateMark && confirmed == that.confirmed && Objects.equals(id, that.id) && Objects.equals(applicantId, that.applicantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, applicantId, mathsMark, englishMark, physicsMark, certificateMark, confirmed);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", applicantId=" + applicantId +
                ", mathsMark=" + mathsMark +
                ", englishMark=" + englishMark +
                ", physicsMark=" + physicsMark +
                ", certificateMark=" + certificateMark +
                ", confirmed=" + confirmed +
                '}';
    }
}
