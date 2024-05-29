package project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import project.domain.University;
import project.service.UniversityService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ComponentScan("project")
@DataJpaTest
public class UniversityServiceTests {
    @Autowired
    UniversityService universityService;

    @Test
    public void testSave() {
        List<University> universities = universityService.readAll();
        assertThat(universities, hasSize(0));

        universityService.save(getTestData());

        universities = universityService.readAll();
        assertThat(universities, hasSize(1));
    }

    @Test
    public void testReadById() {
        List<University> universities = universityService.readAll();
        assertThat(universities, hasSize(0));

        universityService.save(getTestData());

        universities = universityService.readAll();
        assertThat(universities, hasSize(1));

        University university = universityService.readById(universities.get(0).getId());
        assertNotNull(university);
    }

    @Test
    public void testUpdate() {
        List<University> universities = universityService.readAll();
        assertThat(universities, hasSize(0));

        universityService.save(getTestData());

        universities = universityService.readAll();
        assertThat(universities, hasSize(1));

        University university = universities.get(0);
        university.setName("random");
        university.setShortName("random");

        universityService.update(university);

        university = universityService.readAll().get(0);
        assertNotEquals(university.getName(), "KNU");
        assertNotEquals(university.getShortName(), "KNU");
    }

    @Test
    public void testDelete() {
        List<University> universities = universityService.readAll();
        assertThat(universities, hasSize(0));

        universityService.save(getTestData());

        universities = universityService.readAll();
        assertThat(universities, hasSize(1));

        universityService.delete(universities.get(0));

        universities = universityService.readAll();
        assertThat(universities, hasSize(0));
    }

    private University getTestData() {
        return new University("KNU", "KNU", "random", (short) 2000, "", "", "", "", "");
    }
}
