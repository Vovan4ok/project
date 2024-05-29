package project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.DepartmentRepository;
import project.domain.Department;
import project.domain.Faculty;

import java.util.List;

@Service("departmentService")
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public boolean save(Department department) {
        if(!departmentRepository.existsByNameAndFaculty(department.getName(), department.getFaculty())) {
            logger.info("Save the department " + department + " to DB.");
            departmentRepository.save(department);
            return true;
        }
        logger.info("The department with such name and faculty already exists in DB.");
        return false;
    }

    public Department readById(Integer id) {
        logger.info("Get the department with id = " + id + " from DB.");
        return departmentRepository.findById(id).get();
    }

    public List<Department> readAll() {
        logger.info("Get all departments from DB.");
        return departmentRepository.findAll();
    }

    public void update(Department department) {
        logger.info("Update the info about the department with id = " + department.getId() + " to DB.");
        departmentRepository.saveAndFlush(department);
    }

    public void delete(Department department) {
        logger.info("Delete the department = " + department + " from DB.");
        departmentRepository.delete(department);
    }

    public List<Department> readAllByFaculty(Faculty faculty) {
        logger.info("Get all departments by faculty = " + faculty + " from DB.");
        return departmentRepository.readAllByFaculty(faculty);
    }
}
