package project.service;

import org.springframework.web.multipart.MultipartFile;
import project.domain.User;

import java.io.IOException;
import java.util.Base64;

public class UserDTOHelper {
    public static User createUser(String name, String surname, String email, String password, MultipartFile image) throws IOException {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setImage(image.getBytes());
        user.setEncodedImage(Base64.getEncoder().encodeToString(image.getBytes()));
        return user;
    }
}
