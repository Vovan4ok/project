package project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import project.domain.Faculty;
import project.service.FacultyService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ComponentScan("project")
@DataJpaTest
public class FacultyServiceTests {
    @Autowired
    private FacultyService facultyService;

    @Test
    public void testSave() {
        List<Faculty> faculties = facultyService.readAll();
        assertThat(faculties, hasSize(0));

        facultyService.save(getTestFaculty());

        faculties = facultyService.readAll();
        assertThat(faculties, hasSize(1));
    }

    @Test
    public void testReadById() {
        List<Faculty> faculties = facultyService.readAll();
        assertThat(faculties, hasSize(0));

        facultyService.save(getTestFaculty());

        faculties = facultyService.readAll();
        assertThat(faculties, hasSize(1));

        Faculty faculty = faculties.get(0);
        assertNotNull(faculty);
    }

    @Test
    public void testUpdate() {
        List<Faculty> faculties = facultyService.readAll();
        assertThat(faculties, hasSize(0));

        Faculty faculty = getTestFaculty();

        facultyService.save(faculty);

        faculties = facultyService.readAll();
        assertThat(faculties, hasSize(1));

        faculty = facultyService.readAll().get(0);
        faculty.setName("test1");
        faculty.setPlanNumber((short) 200);

        facultyService.update(faculty);

        faculty = facultyService.readAll().get(0);

        assertNotEquals(faculty.getName(), "test");
        assertNotEquals(faculty.getPlanNumber(), Short.valueOf((short) 100));
    }

    @Test
    public void testDelete() {
        List<Faculty> faculties = facultyService.readAll();
        assertThat(faculties, hasSize(0));

        facultyService.save(getTestFaculty());

        faculties = facultyService.readAll();
        assertThat(faculties, hasSize(1));

        facultyService.delete(facultyService.readAll().get(0));

        faculties = facultyService.readAll();
        assertThat(faculties, hasSize(0));
    }

    private Faculty getTestFaculty() {
        return new Faculty("test", (short) 100, "random", 2005, 2000, "random", "random", "random", "random", "random");
    }
}
