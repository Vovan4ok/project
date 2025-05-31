package project.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="specialities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="short_name")
    private String shortName;

    @Column(name="code")
    private String code;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

    @Column(name="level")
    private String level;

    @Column(name="duration")
    private Short duration;

    @Column(name="plan_number")
    private Short planNumber;

    @Column(name="budget_places")
    private Short budgetPlaces;

    @Column(name="accreditation")
    private String accreditation;

    @Column(name="employment_prospects")
    private String employmentProspects;

    @Column(name="curriculum")
    private String curriculum;

    @Column(name="price_per_year")
    private Integer pricePerYear;

    @Column(name="maths_coef")
    private Float mathsCoef;

    @Column(name="ukrainian_coef")
    private Float ukrainianCoef;

    @Column(name="english_coef")
    private Float englishCoef;

    @Column(name="certificate_coef")
    private Float certificateCoef;


    public Speciality(String name, String shortName, Float mathsCoef, Float ukrainianCoef, Float  englishCoef, Float certificateCoef) {
        this.name = name;
        this.shortName = shortName;
        this.mathsCoef = mathsCoef;
        this.ukrainianCoef = ukrainianCoef;
        this.englishCoef = englishCoef;
        this.certificateCoef = certificateCoef;
    }
}
