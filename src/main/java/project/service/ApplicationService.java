package project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.ApplicationRepository;
import project.domain.*;

import java.util.List;

@Service("applicationService")
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    Logger logger = LoggerFactory.getLogger(ApplicationService.class);
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public boolean save(Application application, User user) {
        if(applicationRepository.existsByApplicantAndSpeciality(user, application.getSpeciality()) || applicationRepository.existsByApplicantAndPriority(user, application.getPriority())) {
            return false;
        }
        application.setApplicant(user);
        application.setRatingMark();
        application.setStatus(Status.UNKNOWN);
        logger.info("Save the application = " + application + " to DB.");
        applicationRepository.save(application);
        return true;
    }

    public Application findById(Integer id) {
        logger.info("Get the application with id = " + id + " from DB.");
        return applicationRepository.findById(id).get();
    }
    public List<Application> getApplicationsBySpeciality(Speciality speciality) {
        logger.info("Get all applications with speciality = " + speciality + " from DB.");
        return applicationRepository.readAllBySpecialityAndStatusOrderByRatingMarkDesc(speciality, Status.ACCEPTED);
    }

    public void update(Application application) {
        logger.info("Update the info about application with id = " + application.getId() + " to DB.");
        applicationRepository.saveAndFlush(application);
    }

    public List<Application> readAllByStatus(Status status) {
        logger.info("Get all applications with status = " + status + " from DB.");
        return applicationRepository.readAllByStatus(status);
    }

    public List<Application> readAllByApplicant(User user) {
        logger.info("Get all applications by user = " + user + " from DB.");
        return applicationRepository.readAllByApplicant(user);
    }

    public List<Application> readAll() {
        return applicationRepository.findAll();
    }

    public void delete(Application application) {
        applicationRepository.delete(application);
    }
}
