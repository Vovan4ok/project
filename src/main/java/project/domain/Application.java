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
    private float certificateMark;
    @Column(name="rating_mark")
    private float ratingMark;
    @Column(name="faculty_id")
    private int facultyID;
    @Column(name="confirmed")
    private int confirmed;

    public Application() {
    }

    public Application(Integer id, Integer applicantId, int mathsMark, int englishMark, int physicsMark, float certificateMark, float ratingMark, int facultyID) {
        this.id = id;
        this.applicantId = applicantId;
        this.mathsMark = mathsMark;
        this.englishMark = englishMark;
        this.physicsMark = physicsMark;
        this.certificateMark = certificateMark;
        this.ratingMark = ratingMark;
        this.facultyID = facultyID;
        this.confirmed = 0;
    }

    public Application(Integer applicantId, int mathsMark, int englishMark, int physicsMark, float certificateMark, int facultyID) {
        this.applicantId = applicantId;
        this.mathsMark = mathsMark;
        this.englishMark = englishMark;
        this.physicsMark = physicsMark;
        this.certificateMark = certificateMark;
        this.ratingMark = (mathsMark + englishMark + physicsMark + (certificateMark / 12 * 100)) / 4;
        this.facultyID = facultyID;
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
        this.ratingMark = (this.mathsMark + this.englishMark + this.physicsMark + (this.certificateMark / 12 * 100)) / 4;
    }

    public int getEnglishMark() {
        return englishMark;
    }

    public void setEnglishMark(int englishMark) {
        this.englishMark = englishMark;
        this.ratingMark = (this.mathsMark + this.englishMark + this.physicsMark + (this.certificateMark / 12 * 100)) / 4;
    }

    public int getPhysicsMark() {
        return physicsMark;
    }

    public void setPhysicsMark(int physicsMark) {
        this.physicsMark = physicsMark;
        this.ratingMark = (this.mathsMark + this.englishMark + this.physicsMark + (this.certificateMark / 12 * 100)) / 4;
    }

    public float getCertificateMark() {
        return certificateMark;
    }

    public void setCertificateMark(float certificateMark) {
        this.certificateMark = certificateMark;
        this.ratingMark = (this.mathsMark + this.englishMark + this.physicsMark + (this.certificateMark / 12 * 100)) / 4;
    }

    public float getRatingMark() {
        return ratingMark;
    }

    public void setRatingMark() {
        this.ratingMark = (this.mathsMark + this.englishMark + this.physicsMark + (this.certificateMark / 12 * 200)) / 4;
    }

    public int getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(int facultyID) {
        this.facultyID = facultyID;
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
        return mathsMark == that.mathsMark && englishMark == that.englishMark && physicsMark == that.physicsMark && Float.compare(certificateMark, that.certificateMark) == 0 && Float.compare(ratingMark, that.ratingMark) == 0 && facultyID == that.facultyID && confirmed == that.confirmed && Objects.equals(id, that.id) && Objects.equals(applicantId, that.applicantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, applicantId, mathsMark, englishMark, physicsMark, certificateMark, ratingMark, facultyID, confirmed);
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
                ", ratingMark=" + ratingMark +
                ", facultyID=" + facultyID +
                ", confirmed=" + confirmed +
                '}';
    }
}
