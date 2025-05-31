package project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.domain.Application;
import project.domain.Status;
import project.domain.User;
import project.dto.UserDTOHelper;
import project.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationService applicationService;
    private User user;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/registration")
    public String registration() {
        logger.info("The user is on the registration page.");
        return "registration";
    }

    @PostMapping(value="/registration")
    public String registration(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("patronimic") String patronimic,
            @RequestParam("imageFile") MultipartFile image,
            @RequestParam("marksFile") MultipartFile marks,
            HttpServletRequest request) throws IOException {
        logger.info("User is trying to register");
        if(marks.getOriginalFilename().endsWith(".pdf"))
        {
            User userForm = UserDTOHelper.createUser(name, surname, patronimic, email, password, image.getOriginalFilename(), marks.getOriginalFilename());
            if(userService.save(userForm)) {
                user = userService.getUserByEmail(userForm.getEmail());
                saveFile(image, "src/main/webapp/images/avatars/");
                saveFile(marks, "src/main/webapp/marksFiles/");
                logger.info("Redirecting to the home page.");
                return "redirect:/home";
            }
            request.setAttribute("msg", "Юзер з даним мейлом вже існує!");
            logger.info("Registration wasn't successful.");
            return "registration";
        }
        else
        {
            request.setAttribute("msg", "Файл має бути з роширенням .pdf!");
            logger.info("Registration wasn't successful.");
            return "registration";
        }
    }

    @GetMapping(value={"/", "login"})
    public String login(String error, String logout, HttpServletRequest request) {
        if(error != null) {
            request.setAttribute("error", "Your username or password is invalid");
        }
        if(logout != null) {
            user = null;
            request.setAttribute("message", "You have been logged out");
        }
        logger.info("User is on the login page.");
        return "login";
    }

    @GetMapping(value = "/home")
    public String home(@AuthenticationPrincipal User userPrincipal, HttpServletRequest request) {
        logger.info("User is on the home page.");
        if(this.user == null) {
            this.user = userService.getUserByEmail(userPrincipal.getEmail());
        }
        request.getSession(true).setAttribute("user", user);
        request.setAttribute("role", this.user.getRole().toString());
        if(this.user.getRole().toString().equals("ROLE_ADMIN")) {
            List<Application> applications = applicationService.readAllByStatus(Status.UNKNOWN);
            request.setAttribute("applications", applications);
        }
        return "home";
    }

    @GetMapping(value="/addMenu")
    public String addMenu() {
        return "addMenu";
    }

    public void saveFile(MultipartFile file, String folder) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(folder + file.getOriginalFilename());
        Files.write(path, bytes);
    }

}
