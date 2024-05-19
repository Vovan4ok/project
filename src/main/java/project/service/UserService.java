package project.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.dao.UserRepository;
import project.domain.Role;
import project.domain.User;

import org.slf4j.Logger;

@Service("userService")
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean save(User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(Role.ROLE_USER);
            userRepository.save(user);
            logger.info("Saving the user " + user + " to DB.");
            return true;
        }
        logger.info("The user with such an email is already exists.");
        return false;
    }

    public User findById(Integer id) {
        logger.info("Get the user with id = " + id + " from DB.");
        return userRepository.findById(id).get();
    }
    public User getUserByEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            logger.info("Get the user with email = " + email + " from DB.");
            return userRepository.findByEmail(email).get();
        }
        else {
            logger.info("The user with email + " + email + " doesn't exist.");
            throw new NullPointerException();
        }
    }
}
