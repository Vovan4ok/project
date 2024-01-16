package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.Application;

import java.util.List;
import java.util.Optional;

@Repository("applicationRepository")
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Optional<Application> findById(Integer integer);

    List<Application> readAllByFacultyIDAndConfirmedOrderByRatingMarkDesc(int facultyID, int confirmed);

    List<Application> readAllByConfirmed(Integer confirmed);

}
