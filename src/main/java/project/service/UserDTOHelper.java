package project.service;

import project.domain.User;

public class UserDTOHelper {
    public static User createUser(String name, String surname, String email, String password, String imageName) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(password);
        user.setImageUrl(imageName);
        return user;
    }
}
