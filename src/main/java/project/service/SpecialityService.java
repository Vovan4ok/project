package project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.dao.SpecialityRepository;
import project.domain.Department;
import project.domain.Speciality;

import java.util.List;

@Service("specialityService")
public class SpecialityService {
    @Autowired
    SpecialityRepository specialityRepository;

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    public boolean save(Speciality speciality) {
        if(!specialityRepository.existsByNameAndDepartment(speciality.getName(), speciality.getDepartment())) {
            if(!validateCoefficients(speciality)) {
                logger.info("The sum of coefficients should equal to 1");
                return false;
            }
            logger.info("Save the speciality " + speciality + " to DB.");
            specialityRepository.save(speciality);
            return true;
        }
        logger.info("The speciality with such name and department already exists in DB.");
        return false;
    }

    public Speciality readById(Integer id) {
        logger.info("Get the speciality with id = " + id + " from DB.");
        return specialityRepository.findById(id).get();
    }

    public List<Speciality> readAll() {
        logger.info("Get all specialities from DB.");
        return specialityRepository.findAllSorted(Sort.by("code"));
    }

    public void update(Speciality speciality) {
        logger.info("Update the info about the speciality with id = " + speciality.getId() + " to DB.");
        specialityRepository.saveAndFlush(speciality);
    }

    public void delete(Speciality speciality) {
        logger.info("Delete the speciality = " + speciality + " from DB.");
        specialityRepository.delete(speciality);
    }

    public List<Speciality> readAllByDepartment(Department department) {
        logger.info("Get all faculties by department = " + department + " from DB.");
        return specialityRepository.readAllByDepartmentOrderByCode(department);
    }

    private boolean validateCoefficients(Speciality speciality) {
        return speciality.getMathsCoef() + speciality.getEnglishCoef() + speciality.getUkrainianCoef() + speciality.getCertificateCoef() == 1;
    }
}
