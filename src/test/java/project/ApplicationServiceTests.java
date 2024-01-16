package project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import project.dao.ApplicationRepository;
import project.domain.Application;
import project.domain.User;
import project.service.ApplicationService;

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

    @Test
    public void testSave() {
        User user = new User();
        user.setId(0);

        List<Application> applications = applicationRepository.findAll();
        assertThat(applications, hasSize(0));

        int i = 1;
        for(Application application : getTestData()) {
            user.setId(i);
            applicationService.save(application, user);
            i++;
        }

        applications = applicationRepository.findAll();
        assertThat(applications, hasSize(10));
    }

    @Test
    public void testFindById() {
        User user = new User();
        user.setId(0);

        List<Application> applications = applicationRepository.findAll();
        assertThat(applications, hasSize(0));

        int i = 1;
        for(Application application : getTestData()) {
            user.setId(i);
            applicationService.save(application, user);
            i++;
        }

        applications = applicationRepository.findAll();
        assertThat(applications, hasSize(10));

        assertNotNull(applicationService.findById(applicationRepository.findAll().get(5).getId()));
    }

    @Test
    public void testGetApplicationsByFaculty() {
        User user = new User();
        user.setId(0);

        List<Application> applications = applicationRepository.findAll();
        assertThat(applications, hasSize(0));

        int i = 1;
        for(Application application : getTestData()) {
            user.setId(i);
            applicationService.save(application, user);
            i++;
        }

        applications = applicationRepository.findAll();
        assertThat(applications, hasSize(10));

        applications = applicationService.getApplicationsByFaculty(1, 0);
        assertThat(applications, hasSize(2));
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(0);

        List<Application> applications = applicationRepository.findAll();
        assertThat(applications, hasSize(0));

        applicationService.save(new Application(0, 150, 150, 150, 11, 1), user);

        applications = applicationRepository.findAll();
        assertThat(applications, hasSize(1));

        applications.get(0).setConfirmed(1);
        applicationService.update(applications.get(0));

        assertNotEquals(applicationRepository.findAll().get(0).getConfirmed(), 0);
    }

    @Test
    public void testReadAllByConfirmed() {
        User user = new User();
        user.setId(0);

        List<Application> applications = applicationRepository.findAll();
        assertThat(applications, hasSize(0));

        int i = 1;
        for(Application application : getTestData()) {
            user.setId(i);
            applicationService.save(application, user);
            i++;
        }

        applications = applicationRepository.findAll();
        assertThat(applications, hasSize(10));

        applications.get(5).setConfirmed(1);
        applicationService.update(applications.get(5));
        applications.get(7).setConfirmed(1);
        applicationService.update(applications.get(7));


        applications = applicationService.readAllByConfirmed(1);
        assertThat(applications, hasSize(2));
    }

    private List<Application> getTestData() {
        List<Application> applications = new ArrayList<>();

        Application application = new Application(1, 150, 160, 180, 11, 3);
        Application application2 = new Application(2, 140, 120, 170, 12, 5);
        Application application3 = new Application(3, 160, 130, 130, 11, 8);
        Application application4 = new Application(4, 180, 140, 140, 12, 1);
        Application application5 = new Application(5, 110, 150, 190, 8, 4);
        Application application6 = new Application(6, 120, 160, 150, 6, 8);
        Application application7 = new Application(7, 140, 170, 180, 7, 2);
        Application application8 = new Application(8, 150, 180, 160, 9, 4);
        Application application9 = new Application(9, 180, 190, 170, 10, 1);
        Application application10 = new Application(10, 190, 190, 180, 5, 0);

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
