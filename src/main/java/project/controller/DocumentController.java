package project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import project.domain.*;
import project.service.DocumentService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class DocumentController {
    @Autowired
    DocumentService documentService;

    Logger logger = LoggerFactory.getLogger(DocumentController.class);
    
    @GetMapping(value="/documents")
    public String documents(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("role", user.getRole());
        if (user.getRole() == Role.ROLE_USER) {
            List<Document> documents = documentService.getDocumentsByApplicant(user);
            if (documents.isEmpty()) {
                request.setAttribute("documentsMode", "UPLOAD");
            } 
            else {
                request.setAttribute("documentsMode", "UPLOADED");
                request.setAttribute("documents", documents);
            }
        } 
        else {
            request.setAttribute("documents", documentService.getDocumentsByStatus(Status.UNKNOWN));
        }
        return "documents";
    }
    
    @PostMapping(value="/documents")
    public String documents(@RequestParam("passport") MultipartFile passport, @RequestParam("identificationCode") MultipartFile identificationCode, @RequestParam("address") MultipartFile address, HttpServletRequest request) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (!passport.getOriginalFilename().endsWith(".pdf") || !identificationCode.getOriginalFilename().endsWith(".pdf") || !address.getOriginalFilename().endsWith(".pdf")) {
            request.setAttribute("documentsMode", "UPLOAD");
            request.setAttribute("role", user.getRole());
            return "documents";
        }
        saveFile(passport, "src/main/webapp/documents/" + user.getEmail() + "/");
        saveFile(identificationCode, "src/main/webapp/documents/" + user.getEmail() + "/");
        saveFile(address, "src/main/webapp/documents/" + user.getEmail() + "/");
        Document passportDocument = new Document(user, passport.getOriginalFilename(), DocumentType.PASSPORT);
        Document identificationCodeDocument = new Document(user, identificationCode.getOriginalFilename(), DocumentType.IDENTIFICATION_CODE);
        Document addressDocument = new Document(user, address.getOriginalFilename(), DocumentType.ADDRESS_DOCUMENT);
        documentService.save(passportDocument);
        documentService.save(identificationCodeDocument);
        documentService.save(addressDocument);
        return "redirect:/home";
    }

    @GetMapping(value = "/acceptDocument")
    public String acceptDocument(@RequestParam("id") Integer id) {
        logger.info("Admin is confirming the document with the id = " + id + ".");
        Document document = documentService.findById(id);
        document.setStatus(Status.ACCEPTED);
        documentService.update(document);
        logger.info("The confirmation is successful.");
        return "redirect:/home";
    }

    @GetMapping(value = "/declineDocument")
    public String declineDocument(@RequestParam("id") Integer id) {
        logger.info("Admin is declining the document with the id = " + id + ".");
        Document document = documentService.findById(id);
        document.setStatus(Status.DECLINED);
        documentService.update(document);
        logger.info("Document was declined.");
        return "redirect:/home";
    }

    private void saveFile(MultipartFile file, String folder) throws IOException {
        Path directoryPath = Paths.get(folder);

        if (Files.notExists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        Path filePath = directoryPath.resolve(file.getOriginalFilename());

        Files.write(filePath, file.getBytes());
    }

}
