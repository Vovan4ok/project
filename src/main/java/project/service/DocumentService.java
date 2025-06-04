package project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.DocumentRepository;
import project.domain.Application;
import project.domain.Document;
import project.domain.Status;
import project.domain.User;

import java.util.List;

@Service("documentService")
public class DocumentService {
    @Autowired
    DocumentRepository documentRepository;

    Logger logger = LoggerFactory.getLogger(DocumentService.class);

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public boolean save(Document document) {
        if (documentRepository.existsByApplicantAndDocumentType(document.getApplicant(), document.getDocumentType())) {
            return false;
        }
        logger.info("Save the document = " + document + " to DB.");
        documentRepository.save(document);
        return true;
    }

    public List<Document> getDocumentsByStatus(Status status) {
        logger.info("Get all documents with status = " + status + " from DB.");
        return documentRepository.readAllByStatus(status);
    }
    public List<Document> getDocumentsByApplicant(User applicant) {
        logger.info("Get all documents with user = " + applicant + " from DB.");
        return documentRepository.readAllByApplicant(applicant);
    }

    public void update(Document document) {
        logger.info("Update the info about document with id = " + document.getId() + " to DB.");
        documentRepository.saveAndFlush(document);
    }

    public void delete(Document document) {
        documentRepository.delete(document);
    }

    public Document findById(Integer id) {
        logger.info("Get the document with id = " + id + " from DB.");
        return documentRepository.findById(id).get();
    }
}
