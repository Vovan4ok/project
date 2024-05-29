package project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import project.domain.Department;
import project.domain.Faculty;
import project.domain.University;
import project.service.DepartmentService;
import project.service.FacultyService;
import project.service.UniversityService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ComponentScan("project")
@DataJpaTest
public class DepartmentServiceTest {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    FacultyService facultyService;

    @Autowired
    UniversityService universityService;

    @Test
    public void testSave() {
        List<Department> departments = departmentService.readAll();
        assertThat(departments, hasSize(0));

        University university = getTestUniv();
        Faculty faculty = getTestFaculties().get(0);
        Department department = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);
        department.setFaculty(facultyService.readAll().get(0));
        departmentService.save(department);

        departments = departmentService.readAll();
        assertThat(departments, hasSize(1));
    }

    @Test
    public void testReadById() {
        List<Department> departments = departmentService.readAll();
        assertThat(departments, hasSize(0));

        University university = getTestUniv();
        Faculty faculty = getTestFaculties().get(0);
        Department department = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);
        department.setFaculty(facultyService.readAll().get(0));
        departmentService.save(department);

        departments = departmentService.readAll();
        assertThat(departments, hasSize(1));

        department = departmentService.readById(departments.get(0).getId());
        assertNotNull(department);
    }

    @Test
    public void testUpdate() {
        List<Department> departments = departmentService.readAll();
        assertThat(departments, hasSize(0));

        University university = getTestUniv();
        Faculty faculty = getTestFaculties().get(0);
        Department department = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);
        department.setFaculty(facultyService.readAll().get(0));
        departmentService.save(department);

        departments = departmentService.readAll();
        assertThat(departments, hasSize(1));

        department = departmentService.readAll().get(0);
        department.setName("fafhlafdlkajds");
        department.setShortName("fdhjalfhajsdad");
        departmentService.save(department);

        department = departmentService.readAll().get(0);
        assertNotEquals(department.getName(), "random1");
        assertNotEquals(department.getShortName(), "random1");
    }

    @Test
    public void testDelete() {
        List<Department> departments = departmentService.readAll();
        assertThat(departments, hasSize(0));

        University university = getTestUniv();
        Faculty faculty = getTestFaculties().get(0);
        Department department = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);
        department.setFaculty(facultyService.readAll().get(0));
        departmentService.save(department);

        departments = departmentService.readAll();
        assertThat(departments, hasSize(1));

        departmentService.delete(departments.get(0));

        departments = departmentService.readAll();
        assertThat(departments, hasSize(0));
    }

    @Test
    public void testReadByFaculty() {
        universityService.save(getTestUniv());

        for(Faculty faculty : getTestFaculties()) {
            faculty.setUniversity(universityService.readAll().get(0));
            facultyService.save(faculty);
        }

        List<Faculty> faculties = facultyService.readAll();
        int index = 0;
        for(Department department : getTestData()) {
            department.setFaculty(faculties.get(index % 2));
            departmentService.save(department);
            index++;
        }

        assertThat(departmentService.readAll(), hasSize(5));
        assertThat(departmentService.readAllByFaculty(faculties.get(0)), hasSize(3));
    }

    private List<Department> getTestData() {
        List<Department> departments = new ArrayList<>();

        departments.add(new Department("random1", "random1", null, "", "", (short) 20, (short) 20, (short) 20));
        departments.add(new Department("random2", "random2", null, "", "", (short) 20, (short) 20, (short) 20));
        departments.add(new Department("random3", "random3", null, "", "", (short) 20, (short) 20, (short) 20));
        departments.add(new Department("random4", "random4", null, "", "", (short) 20, (short) 20, (short) 20));
        departments.add(new Department("random5", "random5", null, "", "", (short) 20, (short) 20, (short) 20));

        return departments;
    }

    private List<Faculty> getTestFaculties() {
        List<Faculty> faculties = new ArrayList<>();

        faculties.add(new Faculty("random1", "random1", "random1", 20, 20, "random1", "random1", "random1", "random1", "random1", null));
        faculties.add(new Faculty("random2", "random2", "random2", 20, 20, "random2", "random2", "random2", "random2", "random2", null));

        return faculties;
    }

    private University getTestUniv() {
        return new University("KNU", "KNU", "random", (short) 2000, "random", "random", "random", "random", "random");
    }
}
