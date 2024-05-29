package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.Department;
import project.domain.Faculty;
import project.domain.University;

import java.util.List;
import java.util.Optional;

@Repository("universityRepository")
public interface UniversityRepository extends JpaRepository<University, Integer> {
    Optional<University> findById(Integer id);

    boolean existsByName(String name);
}
