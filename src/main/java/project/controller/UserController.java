package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.domain.Application;
import project.domain.ApplicationModel;
import project.domain.Faculty;
import project.domain.User;
import project.service.ApplicationService;
import project.service.FacultyService;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "registration";
        }
        if(userService.save(userForm)) {
            user = userService.getUserByEmail(userForm.getEmail());
            return "redirect:/home";
        }
        model.addAttribute("msg", "This email already exists!");
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
        return "login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, @AuthenticationPrincipal User userPrincipal, HttpServletRequest request) {
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
                ApplicationModel applicationModel = new ApplicationModel();

                applicationModel.setApplicationId(application.getId());
                applicationModel.setApplicantName(applicant.getName());
                applicationModel.setApplicantSurname(applicant.getSurname());
                applicationModel.setMathsMark(application.getMathsMark());
                applicationModel.setEnglishMark(application.getEnglishMark());
                applicationModel.setPhysicsMark(application.getPhysicsMark());
                applicationModel.setCertificateMark(application.getCertificateMark());
                applicationModel.setRatingMark(application.getRatingMark());
                applicationModel.setFacultyName(faculty.getName());

                applicationModels.add(applicationModel);
            }
            model.addAttribute("applications", applicationModels);
        }
        return "home";
    }

    @RequestMapping(value = "/acceptApplication", method = RequestMethod.GET)
    public String acceptApplication(@RequestParam("id") Integer id, Model model) {
        Application application = applicationService.findById(id);
        application.setConfirmed(1);
        applicationService.update(application);
        return "redirect:/home";
    }
}
