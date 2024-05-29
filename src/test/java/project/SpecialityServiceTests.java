package project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import project.domain.Department;
import project.domain.Faculty;
import project.domain.Speciality;
import project.domain.University;
import project.service.DepartmentService;
import project.service.FacultyService;
import project.service.SpecialityService;
import project.service.UniversityService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ComponentScan("project")
@DataJpaTest
public class SpecialityServiceTests {
    @Autowired
    UniversityService universityService;

    @Autowired
    FacultyService facultyService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    SpecialityService specialityService;

    @Test
    public void testSave() {
        List<Speciality> specialities = specialityService.readAll();
        assertThat(specialities, hasSize(0));

        University university = getTestUniv();
        Faculty faculty = getTestFaculty();
        Department department = getTestDeps().get(0);
        Speciality speciality = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);
        department.setFaculty(facultyService.readAll().get(0));
        departmentService.save(department);
        speciality.setDepartment(departmentService.readAll().get(0));
        specialityService.save(speciality);

        specialities = specialityService.readAll();
        assertThat(specialities, hasSize(1));
    }

    @Test
    public void testReadById() {
        List<Speciality> specialities = specialityService.readAll();
        assertThat(specialities, hasSize(0));

        University university = getTestUniv();
        Faculty faculty = getTestFaculty();
        Department department = getTestDeps().get(0);
        Speciality speciality = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);
        department.setFaculty(facultyService.readAll().get(0));
        departmentService.save(department);
        speciality.setDepartment(departmentService.readAll().get(0));
        specialityService.save(speciality);

        specialities = specialityService.readAll();
        assertThat(specialities, hasSize(1));

        speciality = specialityService.readById(specialities.get(0).getId());
        assertNotNull(speciality);
    }

    @Test
    public void testUpdate() {
        List<Speciality> specialities = specialityService.readAll();
        assertThat(specialities, hasSize(0));

        University university = getTestUniv();
        Faculty faculty = getTestFaculty();
        Department department = getTestDeps().get(0);
        Speciality speciality = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);
        department.setFaculty(facultyService.readAll().get(0));
        departmentService.save(department);
        speciality.setDepartment(departmentService.readAll().get(0));
        specialityService.save(speciality);

        specialities = specialityService.readAll();
        assertThat(specialities, hasSize(1));

        speciality = specialities.get(0);
        speciality.setName("aldhaldsajda");
        speciality.setShortName("skjajhalfaljd");
        specialityService.update(speciality);

        speciality = specialityService.readAll().get(0);
        assertNotEquals(speciality.getName(), "random1");
        assertNotEquals(speciality.getShortName(), "random1");
    }

    @Test
    public void testDelete() {
        List<Speciality> specialities = specialityService.readAll();
        assertThat(specialities, hasSize(0));

        University university = getTestUniv();
        Faculty faculty = getTestFaculty();
        Department department = getTestDeps().get(0);
        Speciality speciality = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);
        department.setFaculty(facultyService.readAll().get(0));
        departmentService.save(department);
        speciality.setDepartment(departmentService.readAll().get(0));
        specialityService.save(speciality);

        specialities = specialityService.readAll();
        assertThat(specialities, hasSize(1));

        specialityService.delete(specialities.get(0));

        specialities = specialityService.readAll();
        assertThat(specialities, hasSize(0));
    }

    @Test
    public void testReadByFaculty() {
        universityService.save(getTestUniv());
        Faculty faculty = getTestFaculty();
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);

        for(Department department : getTestDeps()) {
            department.setFaculty(facultyService.readAll().get(0));
            departmentService.save(department);
        }

        List<Department> departments = departmentService.readAll();
        int index = 0;
        for(Speciality speciality : getTestData()) {
            speciality.setDepartment(departments.get(index % 2));
            specialityService.save(speciality);
            index++;
        }

        assertThat(specialityService.readAll(), hasSize(5));
        assertThat(specialityService.readAllByDepartment(departments.get(0)), hasSize(3));
    }

    private List<Speciality> getTestData() {
        List<Speciality> specialities = new ArrayList<>();

        specialities.add(new Speciality("random1", "random1", 0.3F, 0.3F, 0.3F, 0.1F));
        specialities.add(new Speciality("random2", "random2", 0.3F, 0.3F, 0.3F, 0.1F));
        specialities.add(new Speciality("random3", "random3", 0.3F, 0.3F, 0.3F, 0.1F));
        specialities.add(new Speciality("random4", "random4", 0.3F, 0.3F, 0.3F, 0.1F));
        specialities.add(new Speciality("random5", "random5", 0.3F, 0.3F, 0.3F, 0.1F));

        return specialities;
    }
    private List<Department> getTestDeps() {
        List<Department> departments = new ArrayList<>();

        departments.add(new Department("random1", "random1", null, "", "", (short) 20, (short) 20, (short) 20));
        departments.add(new Department("random2", "random2", null, "", "", (short) 20, (short) 20, (short) 20));

        return departments;
    }
    private Faculty getTestFaculty() {
        return new Faculty("random1", "random1", "random1", 20, 20, "random1", "random1", "random1", "random1", "random1", null);
    }
    private University getTestUniv() {
        return new University("KNU", "KNU", "random", (short) 2000, "random", "random", "random", "random", "random");
    }
}
