package project.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.domain.Department;
import project.domain.Speciality;

import java.util.List;
import java.util.Optional;

@Repository("specialityRepository")
public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {
    Optional<Speciality> findById(Integer id);

    boolean existsByNameAndDepartment(String name, Department department);

    List<Speciality> readAllByDepartmentOrderByCode(Department department);

    @Query("select s from Speciality s")
    List<Speciality> findAllSorted(Sort sort);
}
