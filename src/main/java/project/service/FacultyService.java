package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.FacultyRepository;
import project.domain.Faculty;

import java.util.List;

@Service("facultyService")
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

    public Faculty readById(Integer id) { return facultyRepository.findById(id).get(); }

    public List<Faculty> readAll() {
        return facultyRepository.findAll();
    }

    public void update(Faculty faculty) {
        facultyRepository.saveAndFlush(faculty);
    }

    public void delete(Integer id) {
        facultyRepository.delete(facultyRepository.findById(id).get());
    }
}
