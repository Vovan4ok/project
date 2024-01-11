package project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.ApplicationRepository;
import project.domain.Application;
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
        application.setApplicantId(user.getId());
        application.setRatingMark();
        application.setConfirmed(0);
        logger.info("Save the application = " + application + " to DB.");
        applicationRepository.save(application);
    }

    public Application findById(Integer id) {
        logger.info("Get the application with id = " + id + " from DB.");
        return applicationRepository.findById(id).get();
    }
    public List<Application> getApplicationsByFaculty(Integer facultyId, int confirmed) {
        logger.info("Get all applications with facultyId = " + facultyId + " from DB.");
        return applicationRepository.readAllByFacultyIDAndConfirmedOrderByRatingMarkDesc(facultyId, confirmed);
    }

    public void update(Application application) {
        logger.info("Update the info about application with id = " + application.getId() + " to DB.");
        applicationRepository.saveAndFlush(application);
    }

    public List<Application> readAllByConfirmed(Integer confirmed) {
        logger.info("Get all applications with confirmed = " + confirmed + " from DB.");
        return applicationRepository.readAllByConfirmed(confirmed);
    }
}
