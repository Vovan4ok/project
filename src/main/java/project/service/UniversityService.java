package project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.UniversityRepository;
import project.domain.University;

import java.util.List;

@Service("universityService")
public class UniversityService {
    @Autowired
    UniversityRepository universityRepository;

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public boolean save(University university) {
        if(!universityRepository.existsByName(university.getName())) {
            logger.info("Save the university " + university + " to DB.");
            universityRepository.save(university);
            return true;
        }
        logger.info("The university with such name already exists in DB.");
        return false;
    }

    public University readById(Integer id) {
        logger.info("Get the university with id = " + id + " from DB.");
        return universityRepository.findById(id).get();
    }

    public List<University> readAll() {
        logger.info("Get all universities from DB.");
        return universityRepository.findAll();
    }

    public void update(University university) {
        logger.info("Update the info about the university with id = " + university.getId() + " to DB.");
        universityRepository.saveAndFlush(university);
    }

    public void delete(University university) {
        logger.info("Delete the university = " + university + " from DB.");
        universityRepository.delete(university);
    }
}
