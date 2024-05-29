package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.Department;
import project.domain.Faculty;

import java.util.List;
import java.util.Optional;

@Repository("departmentRepository")
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findById(Integer id);

    boolean existsByNameAndFaculty(String name, Faculty faculty);

    List<Department> readAllByFaculty(Faculty faculty);

}
