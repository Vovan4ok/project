package project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import project.domain.Application;
import project.domain.ApplicationModel;
import project.domain.Faculty;
import project.domain.User;
import project.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private ApplicationService applicationService;
    private User user;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        logger.info("The user is on the registration page.");
        return "registration";
    }

    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public String registration(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("imageFile") MultipartFile image,
            Model model) throws IOException {
        logger.info("User is trying to register");
        User userForm = UserDTOHelper.createUser(name, surname, email, password, image);
        if(userService.save(userForm)) {
            user = userService.getUserByEmail(userForm.getEmail());
            logger.info("Redirecting to the home page.");
            return "redirect:/home";
        }
        model.addAttribute("msg", "This email already exists!");
        logger.info("Registration wasn't successful.");
        return "registration";
    }

    @RequestMapping(value={"/", "login"}, method=RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if(error != null) {
            model.addAttribute("error", "Your username or password is invalid");
        }
        if(logout != null) {
            model.addAttribute("message", "You have been logged out");
        }
        logger.info("User is on the login page.");
        return "login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, @AuthenticationPrincipal User userPrincipal, HttpServletRequest request) {
        logger.info("User is on the home page.");
        if(this.user == null) {
            this.user = userService.getUserByEmail(userPrincipal.getEmail());
        }
        request.getSession(true).setAttribute("user", user);
        model.addAttribute("role", this.user.getRole());
        if(this.user.getRole().equals("ADMIN")) {
            List<Application> applications = applicationService.readAllByConfirmed(0);
            List<ApplicationModel> applicationModels = new ArrayList<>();
            for(Application application : applications) {
                User applicant = userService.findById(application.getApplicantId());
                Faculty faculty = facultyService.readById(application.getFacultyID());

                applicationModels.add(ApplicationModelDTOHelper.createEntityForAdmin(
                        application.getId(),
                        applicant.getName(),
                        applicant.getSurname(),
                        application.getMathsMark(),
                        application.getEnglishMark(),
                        application.getPhysicsMark(),
                        application.getCertificateMark(),
                        application.getRatingMark(),
                        faculty.getName()
                ));
            }
            model.addAttribute("applications", applicationModels);
        }
        return "home";
    }

    @RequestMapping(value = "/acceptApplication", method = RequestMethod.GET)
    public String acceptApplication(@RequestParam("id") Integer id, Model model) {
        logger.info("Admin is confirming the application with the id = " + id + ".");
        Application application = applicationService.findById(id);
        application.setConfirmed(1);
        applicationService.update(application);
        logger.info("The confirmation is successful.");
        return "redirect:/home";
    }
}
