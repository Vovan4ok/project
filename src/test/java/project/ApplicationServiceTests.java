package project;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import project.domain.*;
import project.service.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ComponentScan("project")
@DataJpaTest
public class ApplicationServiceTests {
    @Autowired
    UniversityService universityService;

    @Autowired
    FacultyService facultyService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    SpecialityService specialityService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    UserService userService;

    @Test
    public void testSave() {
        List<Application> applications = applicationService.readAll();
        assertThat(applications, hasSize(0));

        University university = getTestUniversity();
        Faculty faculty = getTestFaculty();
        Department department = getTestDepartment();
        Speciality speciality = getTestSpecialities().get(0);
        User user = getTestApplicants().get(0);
        Application application = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);
        department.setFaculty(facultyService.readAll().get(0));
        departmentService.save(department);
        speciality.setDepartment(departmentService.readAll().get(0));
        specialityService.save(speciality);
        userService.save(user);
        application.setSpeciality(specialityService.readAll().get(0));
        applicationService.save(application, userService.getUserByEmail("random1"));

        applications = applicationService.readAll();
        assertThat(applications, hasSize(1));
    }

    @Test
    public void testFindById() {
        List<Application> applications = applicationService.readAll();
        assertThat(applications, hasSize(0));

        University university = getTestUniversity();
        Faculty faculty = getTestFaculty();
        Department department = getTestDepartment();
        Speciality speciality = getTestSpecialities().get(0);
        User user = getTestApplicants().get(0);
        Application application = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);
        department.setFaculty(facultyService.readAll().get(0));
        departmentService.save(department);
        speciality.setDepartment(departmentService.readAll().get(0));
        specialityService.save(speciality);
        userService.save(user);
        application.setSpeciality(specialityService.readAll().get(0));
        applicationService.save(application, userService.getUserByEmail("random1"));

        applications = applicationService.readAll();
        assertThat(applications, hasSize(1));

        application = applicationService.findById(applications.get(0).getId());
        assertNotNull(application);
    }

    @Test
    public void testGetApplicationsBySpeciality() {
        List<Application> applications = applicationService.readAll();
        assertThat(applications, hasSize(0));

        University university = getTestUniversity();
        Faculty faculty = getTestFaculty();
        Department department = getTestDepartment();
        User user = getTestApplicants().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);
        department.setFaculty(facultyService.readAll().get(0));
        departmentService.save(department);
        userService.save(user);
        for(Speciality speciality : getTestSpecialities()) {
            speciality.setDepartment(departmentService.readAll().get(0));
            specialityService.save(speciality);
        }

        int index = 0;
        List<Speciality> specialities = specialityService.readAll();
        for(Application application : getTestData()) {
            application.setSpeciality(specialities.get(index % 2));
            applicationService.save(application, userService.getUserByEmail("random1"));
            index++;
        }

        assertThat(applicationService.readAll(), hasSize(2));
        for(Application application : applicationService.readAll()) {
            application.setStatus(Status.ACCEPTED);
            applicationService.update(application);
        }
        assertThat(applicationService.getApplicationsBySpeciality(specialities.get(0)), hasSize(1));
    }

    @Test
    public void testUpdate() {
        List<Application> applications = applicationService.readAll();
        assertThat(applications, hasSize(0));

        University university = getTestUniversity();
        Faculty faculty = getTestFaculty();
        Department department = getTestDepartment();
        Speciality speciality = getTestSpecialities().get(0);
        User user = getTestApplicants().get(0);
        Application application = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);
        department.setFaculty(facultyService.readAll().get(0));
        departmentService.save(department);
        speciality.setDepartment(departmentService.readAll().get(0));
        specialityService.save(speciality);
        userService.save(user);
        application.setSpeciality(specialityService.readAll().get(0));
        applicationService.save(application, userService.getUserByEmail("random1"));

        applications = applicationService.readAll();
        assertThat(applications, hasSize(1));

        application = applications.get(0);
        application.setMathsMark((short) 50);
        application.setPhysicsMark((short) 50);
        applicationService.update(application);

        assertNotEquals(applicationService.readAll().get(0).getMathsMark(), Short.valueOf( (short) 20));
        assertNotEquals(applicationService.readAll().get(0).getPhysicsMark(), Short.valueOf( (short) 20));
    }

    @Test
    public void testReadAllByStatus() {
        List<Application> applications = applicationService.readAll();
        assertThat(applications, hasSize(0));

        University university = getTestUniversity();
        Faculty faculty = getTestFaculty();
        Department department = getTestDepartment();
        User user = getTestApplicants().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);
        department.setFaculty(facultyService.readAll().get(0));
        departmentService.save(department);
        userService.save(user);
        for(Speciality speciality : getTestSpecialities()) {
            speciality.setDepartment(departmentService.readAll().get(0));
            specialityService.save(speciality);
        }

        int index = 0;
        List<Speciality> specialities = specialityService.readAll();
        for(Application application : getTestData()) {
            application.setSpeciality(specialities.get(index % 2));
            applicationService.save(application, userService.getUserByEmail("random1"));
            index++;
        }
        assertThat(applicationService.readAll(), hasSize(2));
        index = 0;
        for(Application application : applicationService.readAll()) {
            if(index % 2 == 0) {
                application.setStatus(Status.ACCEPTED);
                applicationService.update(application);
            }
            index++;
        }
        assertThat(applicationService.readAllByStatus(Status.ACCEPTED), hasSize(1));
    }

    @Test
    public void testReadAllByApplicant() {
        List<Application> applications = applicationService.readAll();
        assertThat(applications, hasSize(0));

        University university = getTestUniversity();
        Faculty faculty = getTestFaculty();
        Department department = getTestDepartment();

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);
        department.setFaculty(facultyService.readAll().get(0));
        departmentService.save(department);
        for(Speciality speciality : getTestSpecialities()) {
            speciality.setDepartment(departmentService.readAll().get(0));
            specialityService.save(speciality);
        }
        for(User user : getTestApplicants()) {
            userService.save(user);
        }

        List<User> users = userService.findAll();
        int index = 0;
        for(Application application : getTestData()) {
            application.setSpeciality(specialityService.readAll().get(0));
            applicationService.save(application, users.get(index % 2));
            index++;
        }
        assertThat(applicationService.readAll(), hasSize(2));
        assertThat(applicationService.readAllByApplicant(users.get(0)), hasSize(1));
    }

    private List<Application> getTestData() {
        List<Application> applications = new ArrayList<>();

        applications.add(new Application(null, (short) 20, (short) 20, (short) 20,  10F, null, Status.ACCEPTED));
        applications.add(new Application(null, (short) 20, (short) 20, (short) 20,  10F, null, Status.ACCEPTED));
        applications.add(new Application(null, (short) 20, (short) 20, (short) 20,  10F, null, Status.ACCEPTED));
        applications.add(new Application(null, (short) 20, (short) 20, (short) 20,  10F, null, Status.ACCEPTED));
        applications.add(new Application(null, (short) 20, (short) 20, (short) 20,  10F, null, Status.ACCEPTED));

        return applications;
    }

    private List<User> getTestApplicants() {
        List<User> users = new ArrayList<>();

        users.add(new User("random1", "random1", "random1", "random1", "random1", Role.ROLE_USER, "random1"));
        users.add(new User("random2", "random2", "random2", "random2", "random2", Role.ROLE_USER, "random2"));

        return users;
    }

    private List<Speciality> getTestSpecialities() {
        List<Speciality> specialities = new ArrayList<>();

        specialities.add(new Speciality("random1", "random1", 0.3F, 0.3F, 0.3F, 0.1F));
        specialities.add(new Speciality("random2", "random2", 0.3F, 0.3F, 0.3F, 0.1F));

        return specialities;
    }

    private Department getTestDepartment() {
        return new Department("random1", "random1", null, "", "", (short) 20, (short) 20, (short) 20);
    }

    private Faculty getTestFaculty() {
        return new Faculty("random1", "random1", "random1", 20, 20, "random1", "random1", "random1", "random1", "random1", null);
    }
    private University getTestUniversity() {
        return new University("KNU", "KNU", "random", (short) 2000, "random", "random", "random", "random", "random");
    }
}
