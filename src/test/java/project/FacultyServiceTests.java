package project;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import project.domain.Faculty;
import project.domain.University;
import project.service.FacultyService;
import project.service.UniversityService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ComponentScan("project")
@DataJpaTest
public class FacultyServiceTests {
    @Autowired
    private FacultyService facultyService;

    @Autowired
    private UniversityService universityService;

    @Test
    public void testSave() {
        List<Faculty> faculties = facultyService.readAll();
        assertThat(faculties, Matchers.hasSize(0));

        University university = getTestUnivs().get(0);
        Faculty faculty = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);

        faculties = facultyService.readAll();
        assertThat(faculties, Matchers.hasSize(1));
    }

    @Test
    public void testReadById() {
        List<Faculty> faculties = facultyService.readAll();
        assertThat(faculties, Matchers.hasSize(0));

        University university = getTestUnivs().get(0);
        Faculty faculty = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);

        faculties = facultyService.readAll();
        assertThat(faculties, Matchers.hasSize(1));

        faculty = facultyService.readById(faculties.get(0).getId());
        assertNotNull(faculty);
    }

    @Test
    public void testUpdate() {
        List<Faculty> faculties = facultyService.readAll();
        assertThat(faculties, Matchers.hasSize(0));

        University university = getTestUnivs().get(0);
        Faculty faculty = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);

        faculties = facultyService.readAll();
        assertThat(faculties, Matchers.hasSize(1));

        faculty = faculties.get(0);
        faculty.setName("sjafjfasf");
        faculty.setShortName("falsdkldasdj");

        facultyService.update(faculty);

        faculty = facultyService.readAll().get(0);
        assertNotEquals(faculty.getName(), "random1");
        assertNotEquals(faculty.getShortName(), "random1");
    }

    @Test
    public void testDelete() {
        List<Faculty> faculties = facultyService.readAll();
        assertThat(faculties, Matchers.hasSize(0));

        University university = getTestUnivs().get(0);
        Faculty faculty = getTestData().get(0);

        universityService.save(university);
        faculty.setUniversity(universityService.readAll().get(0));
        facultyService.save(faculty);

        faculties = facultyService.readAll();
        assertThat(faculties, Matchers.hasSize(1));

        facultyService.delete(faculties.get(0));

        faculties = facultyService.readAll();
        assertThat(faculties, Matchers.hasSize(0));
    }

    @Test
    public void testReadAllByUniversity() {
        for(University university : getTestUnivs()) {
            universityService.save(university);
        }

        List<University> universities = universityService.readAll();
        int index = 0;
        for(Faculty faculty : getTestData()) {
            faculty.setUniversity(universities.get(index % 2));
            facultyService.save(faculty);
            index++;
        }

        assertThat(facultyService.readAll(), Matchers.hasSize(5));
        assertThat(facultyService.readAllByUniversity(universities.get(0)), Matchers.hasSize(3));
    }

    private List<Faculty> getTestData() {
        List<Faculty> faculties = new ArrayList<>();

        faculties.add(new Faculty("random1", "random1", "random1", 20, 20, "random1", "random1", "random1", "random1", "random1", null));
        faculties.add(new Faculty("random2", "random2", "random2", 20, 20, "random2", "random2", "random2", "random2", "random2", null));
        faculties.add(new Faculty("random3", "random3", "random3", 20, 20, "random3", "random3", "random3", "random3", "random3", null));
        faculties.add(new Faculty("random4", "random4", "random4", 20, 20, "random4", "random4", "random4", "random4", "random4", null));
        faculties.add(new Faculty("random5", "random5", "random5", 20, 20, "random5", "random5", "random5", "random5", "random5", null));

        return faculties;
    }

    private List<University> getTestUnivs() {
        List<University> universities = new ArrayList<>();

        universities.add(new University("KNU", "KNU", "random", (short) 2000, "random", "random", "random", "random", "random"));
        universities.add(new University("KPI", "KPI", "random2", (short) 2000, "random2", "random2", "random2", "random2", "random2"));

        return universities;
    }
}
