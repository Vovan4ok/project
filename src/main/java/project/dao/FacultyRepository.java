package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.Faculty;

import java.util.Optional;

@Repository("facultyRepository")
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    Optional<Faculty> findById(Integer id);
    boolean existsByName(String name);
}
