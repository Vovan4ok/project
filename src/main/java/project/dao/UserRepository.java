package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.domain.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

}
