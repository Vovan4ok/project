package project.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="applicant_id")
    private User applicant;

    @Column(name="document_path")
    private String path;

    @Column(name="document_type")
    private DocumentType documentType;

    @Column(name="status")
    private Status status;

    public Document(User applicant, String path, DocumentType documentType)
    {
        this.applicant = applicant;
        this.path = path;
        this.documentType = documentType;
        this.status = Status.UNKNOWN;
    }
}
