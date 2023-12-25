package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.Application;

@Repository("applicationRepository")
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
