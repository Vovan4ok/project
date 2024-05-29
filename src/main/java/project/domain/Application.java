package project.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="applicant_id")
    private User applicant;

    @Column(name="maths_mark")
    private Short mathsMark;

    @Column(name="english_mark")
    private Short englishMark;

    @Column(name="physics_mark")
    private Short physicsMark;

    @Column(name="certificate_mark")
    private Float certificateMark;

    @Column(name="rating_mark")
    private Float ratingMark;

    @ManyToOne
    @JoinColumn(name="speciality_id")
    private Speciality speciality;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Application(User applicant, Short mathsMark, Short englishMark, Short physicsMark, Float certificateMark, Speciality speciality) {
        this.applicant = applicant;
        this.mathsMark = mathsMark;
        this.englishMark = englishMark;
        this.physicsMark = physicsMark;
        this.certificateMark = certificateMark;
        this.speciality = speciality;
        this.status = Status.UNKNOWN;
        this.setRatingMark();
    }

    public Application(User applicant, Short mathsMark, Short englishMark, Short physicsMark, Float certificateMark, Speciality speciality, Status status) {
        this.applicant = applicant;
        this.mathsMark = mathsMark;
        this.englishMark = englishMark;
        this.physicsMark = physicsMark;
        this.certificateMark = certificateMark;
        this.speciality = speciality;
        this.status = status;
    }

    public void setRatingMark() {
        this.ratingMark = (float) (this.mathsMark * this.speciality.getMathsCoef() + this.englishMark * this.speciality.getEnglishCoef() + this.physicsMark * this.speciality.getPhysicsCoef() + this.certificateMark * 16.67 * this.speciality.getCertificateCoef());
    }
}
