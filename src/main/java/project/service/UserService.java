package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.dao.UserRepository;
import project.domain.User;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public boolean save(User user) {
        if(!userRepository.existsByEmail(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("DEFAULT");
            userRepository.save(user);
            return true;
        }
        return false;
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
