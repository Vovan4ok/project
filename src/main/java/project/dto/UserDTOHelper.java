package project.dto;

import project.domain.User;

public class UserDTOHelper {
    public static User createUser(String name, String surname, String patronimic, String email, String password, String imageName) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setPatronimic(patronimic);
        user.setEmail(email);
        user.setPassword(password);
        user.setImageUrl(imageName);
        return user;
    }
}
