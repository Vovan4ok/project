package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.Application;
import project.domain.Faculty;
import project.domain.Status;
import project.domain.User;

import java.util.List;
import java.util.Optional;

@Repository("applicationRepository")
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Optional<Application> findById(Integer id);

    List<Application> readAllByFacultyAndStatusOrderByRatingMarkDesc(Faculty faculty, Status status);

    List<Application> readAllByStatus(Status status);

    List<Application> readAllByApplicant(User user);

}
