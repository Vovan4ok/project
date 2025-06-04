package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.*;
import project.service.DocumentService;

import java.util.List;
import java.util.Optional;

@Repository("documentRepository")
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Optional<Document> findById(Integer id);

    List<Document> readAllByApplicant(User user);

    Optional<Document> findByDocumentType(DocumentType documentType);

    boolean existsByApplicantAndDocumentType(User applicant, DocumentType documentType);

    List<Document> readAllByStatus(Status status);
}
