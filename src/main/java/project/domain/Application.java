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

    @Column(name="ukrainian_mark")
    private Short ukrainianMark;

    @Column(name="certificate_mark")
    private Float certificateMark;

    @Column(name="rating_mark")
    private Float ratingMark;

    @ManyToOne
    @JoinColumn(name="speciality_id")
    private Speciality speciality;

    @Column(name="priority")
    private Short priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Application(User applicant, Short mathsMark, Short englishMark, Short ukrainianMark, Float certificateMark, Speciality speciality, Short priority) {
        this.applicant = applicant;
        this.mathsMark = mathsMark;
        this.englishMark = englishMark;
        this.ukrainianMark = ukrainianMark;
        this.certificateMark = certificateMark;
        this.speciality = speciality;
        this.priority = priority;
        this.status = Status.UNKNOWN;
        this.setRatingMark();
    }

    public Application(User applicant, Short mathsMark, Short englishMark, Short ukrainianMark, Float certificateMark, Speciality speciality, Short priority, Status status) {
        this.applicant = applicant;
        this.mathsMark = mathsMark;
        this.englishMark = englishMark;
        this.ukrainianMark = ukrainianMark;
        this.certificateMark = certificateMark;
        this.speciality = speciality;
        this.priority = priority;
        this.status = status;
    }

    public void setRatingMark() {
        float rawMark = (float) (
                this.mathsMark * this.speciality.getMathsCoef() +
                        this.englishMark * this.speciality.getEnglishCoef() +
                        this.ukrainianMark * this.speciality.getUkrainianCoef() +
                        this.certificateMark * 16.67 * this.speciality.getCertificateCoef()
        );
        this.ratingMark = Math.round(rawMark * 10f) / 10f;
    }

}
