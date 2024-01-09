package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.Application;

import java.util.List;
import java.util.Optional;

@Repository("applicationRepository")
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Optional<Application> findById(Integer integer);

    List<Application> readAllByFacultyIDOrderByRatingMark(Integer facultyId);

    List<Application> readAllByConfirmed(Integer confirmed);
}
