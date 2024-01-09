package project.service;

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

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public void save(Application application, User user) {
        application.setApplicantId(user.getId());
        application.setRatingMark();
        application.setConfirmed(0);
        applicationRepository.save(application);
    }

    public Application findById(Integer id) {
        return applicationRepository.findById(id).get();
    }
    public List<Application> getApplicationsByFaculty(Integer facultyId, int confirmed) {
        return applicationRepository.readAllByFacultyIDAndConfirmedOrderByRatingMarkDesc(facultyId, confirmed);
    }

    public void update(Application application) {
        applicationRepository.saveAndFlush(application);
    }

    public List<Application> readAllByConfirmed(Integer confirmed) {
        return applicationRepository.readAllByConfirmed(confirmed);
    }
}
