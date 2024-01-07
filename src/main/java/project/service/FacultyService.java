package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.FacultyRepository;
import project.domain.Faculty;

@Service
public class FacultyService {
    @Autowired
    FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public boolean save(Faculty faculty) {
        if(!facultyRepository.existsByName(faculty.getName())) {
            facultyRepository.save(faculty);
            return true;
        }
        return false;
    }
}
