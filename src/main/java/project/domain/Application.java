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
    @JoinColumn(name="faculty_id")
    private Faculty faculty;
    @Column(name="confirmed")
    private Boolean confirmed;

    public Application(User applicant, Short mathsMark, Short englishMark, Short physicsMark, Float certificateMark, Faculty faculty) {
        this.applicant = applicant;
        this.mathsMark = mathsMark;
        this.englishMark = englishMark;
        this.physicsMark = physicsMark;
        this.certificateMark = certificateMark;
        this.ratingMark = (mathsMark + englishMark + physicsMark + (certificateMark / 12 * 200)) / 4;
        this.faculty = faculty;
        this.confirmed = false;
    }

    public void setRatingMark() {
        this.ratingMark = (this.mathsMark + this.englishMark + this.physicsMark + (this.certificateMark / 12 * 200)) / 4;
    }
}
