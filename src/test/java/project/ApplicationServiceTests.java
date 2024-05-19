package project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import project.dao.ApplicationRepository;
import project.domain.Application;
import project.domain.Faculty;
import project.domain.Role;
import project.domain.User;
import project.service.ApplicationService;
import project.service.FacultyService;
import project.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ComponentScan("project")
@DataJpaTest
public class ApplicationServiceTests {
    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private UserService userService;

    @Test
    public void testSave() {
        List<Application> applications = applicationRepository.findAll();
        assertThat(applications, hasSize(0));

        for(Application application : getTestData()) {
            userService.save(application.getApplicant());
            facultyService.save(application.getFaculty());
            Faculty faculty = facultyService.getFacultyByName(application.getFaculty().getName());
            application.setFaculty(faculty);
            User user = userService.getUserByEmail(application.getApplicant().getEmail());
            applicationService.save(application, user);
        }

        applications = applicationRepository.findAll();
        assertThat(applications, hasSize(10));
    }

    @Test
    public void testFindById() {
        List<Application> applications = applicationRepository.findAll();
        assertThat(applications, hasSize(0));

        for(Application application : getTestData()) {
            userService.save(application.getApplicant());
            facultyService.save(application.getFaculty());
            Faculty faculty = facultyService.getFacultyByName(application.getFaculty().getName());
            application.setFaculty(faculty);
            User user = userService.getUserByEmail(application.getApplicant().getEmail());
            applicationService.save(application, user);
        }

        applications = applicationRepository.findAll();
        assertThat(applications, hasSize(10));

        assertNotNull(applicationService.findById(applicationRepository.findAll().get(5).getId()));
    }

    @Test
    public void testGetApplicationsByFaculty() {
        List<Application> applications = applicationRepository.findAll();
        assertThat(applications, hasSize(0));

        for(Application application : getTestData()) {
            userService.save(application.getApplicant());
            facultyService.save(application.getFaculty());
            Faculty faculty = facultyService.getFacultyByName(application.getFaculty().getName());
            application.setFaculty(faculty);
            User user = userService.getUserByEmail(application.getApplicant().getEmail());
            applicationService.save(application, user);
        }

        applications = applicationRepository.findAll();
        assertThat(applications, hasSize(10));

        applications = applicationService.getApplicationsByFacultyAndConfirmed(facultyService.getFacultyByName("FIT"), false);
        assertThat(applications, hasSize(2));
    }

    @Test
    public void testUpdate() {
        User user = new User(0, "vova", "vova", "vova", "vova", Role.ROLE_USER, "vova");
        facultyService.save(new Faculty((short) 1, "FIT", (short) 100));

        List<Application> applications = applicationRepository.findAll();
        assertThat(applications, hasSize(0));

        userService.save(user);
        user = userService.getUserByEmail(user.getEmail());
        applicationService.save(new Application(user, (short) 150, (short) 150, (short) 150, 11F, facultyService.getFacultyByName("FIT")), user);

        applications = applicationRepository.findAll();
        assertThat(applications, hasSize(1));

        applications.get(0).setConfirmed(true);
        applicationService.update(applications.get(0));

        assertNotEquals(applicationRepository.findAll().get(0).getConfirmed(), false);
    }

    @Test
    public void testReadAllByConfirmed() {
        List<Application> applications = applicationRepository.findAll();
        assertThat(applications, hasSize(0));

        for(Application application : getTestData()) {
            userService.save(application.getApplicant());
            facultyService.save(application.getFaculty());
            Faculty faculty = facultyService.getFacultyByName(application.getFaculty().getName());
            application.setFaculty(faculty);
            User user = userService.getUserByEmail(application.getApplicant().getEmail());
            applicationService.save(application, user);
        }

        applications = applicationRepository.findAll();
        assertThat(applications, hasSize(10));

        applications.get(5).setConfirmed(true);
        applicationService.update(applications.get(5));
        applications.get(7).setConfirmed(true);
        applicationService.update(applications.get(7));


        applications = applicationService.readAllByConfirmed(true);
        assertThat(applications, hasSize(2));
    }

    private List<Application> getTestData() {
        List<Application> applications = new ArrayList<>();

        Application application = new Application(new User(1, "vova", "vova", "vova", "vova", Role.ROLE_USER, "vova"), (short) 150, (short) 160, (short) 180, 11F, new Faculty((short) 3, "monitor", (short) 50));
        Application application2 = new Application(new User(1, "maks", "vova", "vova", "vova", Role.ROLE_USER, "vova"), (short) 140, (short) 120, (short) 170, 12F, new Faculty((short) 5, "wow", (short) 50));
        Application application3 = new Application(new User(1, "ivan", "vova", "vova", "vova", Role.ROLE_USER, "vova"), (short) 160, (short) 130, (short) 130, 11F, new Faculty((short) 8, "hi", (short) 50));
        Application application4 = new Application(new User(1, "vasyl", "vova", "vova", "vova", Role.ROLE_USER, "vova"), (short) 180, (short) 140, (short) 140, 12F, new Faculty((short) 1, "FIT", (short) 50));
        Application application5 = new Application(new User(1, "artem", "vova", "vova", "vova", Role.ROLE_USER, "vova"), (short) 110, (short) 150, (short) 190, 8F, new Faculty((short) 4, "hello", (short) 50));
        Application application6 = new Application(new User(1, "igor", "vova", "vova", "vova", Role.ROLE_USER, "vova"), (short) 120, (short) 160, (short) 150, 6F, new Faculty((short) 8, "hi", (short) 50));
        Application application7 = new Application(new User(1, "vlad", "vova", "vova", "vova", Role.ROLE_USER, "vova"), (short) 140, (short) 170, (short) 180, 7F, new Faculty((short) 2, "Good", (short) 50));
        Application application8 = new Application(new User(1, "sasha", "vova", "vova", "vova", Role.ROLE_USER, "vova"), (short) 150, (short) 180, (short) 160, 9F, new Faculty((short) 4, "hello", (short) 50));
        Application application9 = new Application(new User(1, "nikita", "vova", "vova", "vova", Role.ROLE_USER, "vova"), (short) 180, (short) 190, (short) 170, 10F, new Faculty((short) 1, "FIT", (short) 50));
        Application application10 = new Application(new User(1, "alex", "vova", "vova", "vova", Role.ROLE_USER, "vova"), (short) 190, (short) 190, (short) 180, 5F, new Faculty((short) 0, "world", (short) 50));

        applications.add(application);
        applications.add(application2);
        applications.add(application3);
        applications.add(application4);
        applications.add(application5);
        applications.add(application6);
        applications.add(application7);
        applications.add(application8);
        applications.add(application9);
        applications.add(application10);

        return applications;
    }
}
