package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.Faculty;
import project.domain.University;

import java.util.List;
import java.util.Optional;

@Repository("facultyRepository")
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    Optional<Faculty> findById(Integer id);

    List<Faculty> readAllByUniversity(University university);

    boolean existsByNameAndUniversity(String name, University university);

}
