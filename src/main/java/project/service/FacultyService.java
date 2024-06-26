package project.service;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.FacultyRepository;
import project.domain.Faculty;
import project.domain.University;

import java.util.List;

@Service("facultyService")
public class FacultyService {
    @Autowired
    FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public boolean save(Faculty faculty) {
        if(!facultyRepository.existsByNameAndUniversity(faculty.getName(), faculty.getUniversity())) {
            logger.info("Save the faculty " + faculty + " to DB.");
            facultyRepository.save(faculty);
            return true;
        }
        logger.info("The faculty with such a name already exists in DB.");
        return false;
    }

    public Faculty readById(Integer id) {
        logger.info("Get the faculty with id = " + id + " from DB.");
        return facultyRepository.findById(id).get();
    }

    public List<Faculty> readAll() {
        logger.info("Get all faculties from DB.");
        return facultyRepository.findAll();
    }

    public void update(Faculty faculty) {
        logger.info("Update the info about the faculty with id = " + faculty.getId() + " to DB.");
        facultyRepository.saveAndFlush(faculty);
    }

    public void delete(Faculty faculty) {
        logger.info("Delete the faculty = " + faculty + " from DB.");
        facultyRepository.delete(faculty);
    }

    public List<Faculty> readAllByUniversity(University university) {
        logger.info("Get all faculties by university = " + university + " from DB.");
        return facultyRepository.readAllByUniversity(university);
    }
}
