package project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.ApplicationRepository;
import project.domain.Application;
import project.domain.Faculty;
import project.domain.Status;
import project.domain.User;

import java.util.List;

@Service("aplicationService")
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    Logger logger = LoggerFactory.getLogger(ApplicationService.class);
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public void save(Application application, User user) {
        application.setApplicant(user);
        application.setRatingMark();
        application.setStatus(Status.UNKNOWN);
        logger.info("Save the application = " + application + " to DB.");
        applicationRepository.save(application);
    }

    public Application findById(Integer id) {
        logger.info("Get the application with id = " + id + " from DB.");
        return applicationRepository.findById(id).get();
    }
    public List<Application> getApplicationsByFacultyAndStatus(Faculty faculty, Status status) {
        logger.info("Get all applications with facultyId = " + faculty + " from DB.");
        return applicationRepository.readAllByFacultyAndStatusOrderByRatingMarkDesc(faculty, status);
    }

    public void update(Application application) {
        logger.info("Update the info about application with id = " + application.getId() + " to DB.");
        applicationRepository.saveAndFlush(application);
    }

    public List<Application> readAllByStatus(Status status) {
        logger.info("Get all applications with confirmed = " + status + " from DB.");
        return applicationRepository.readAllByStatus(status);
    }

    public void delete(Application application) {
        logger.info("Delete application = " + application + " from DB.");
        applicationRepository.delete(application);
    }

    public List<Application> readAllByApplicant(User user) {
        logger.info("Get all applications by user = " + user + " from DB.");
        return applicationRepository.readAllByApplicant(user);
    }
}
