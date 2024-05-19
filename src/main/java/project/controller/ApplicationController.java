package project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.domain.Application;
import project.domain.Faculty;
import project.domain.User;
import project.service.ApplicationDTO;
import project.service.ApplicationService;
import project.service.FacultyService;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ApplicationController {
    @Autowired
    private FacultyService facultyService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @GetMapping(value="/application")
    public String application(HttpServletRequest request) {
        logger.info("The user is on the application page.");
        request.setAttribute("user", (User) request.getSession().getAttribute("user"));
        request.setAttribute("faculties", facultyService.readAll());
        return "application";
    }

    @PostMapping(value = "/application")
    public String application(@ModelAttribute("applicationForm") Application applicationForm, BindingResult bindingResult, @RequestParam("facultyId") Short facultyId, HttpServletRequest request){
        logger.info("User is trying to apply.");
        if(bindingResult.hasErrors()) {
            logger.info("The application wasn't successful.");
            return "application";
        }
        User user = (User) request.getSession().getAttribute("user");
        applicationForm.setFaculty(facultyService.readById(facultyId));
        user = userService.getUserByEmail(user.getEmail());
        applicationService.save(applicationForm, user);
        logger.info("User made an application");
        return "redirect:/home";
    }

    @GetMapping(value = "/list-of-applicants/{faculty_id}")
    public String listOfApplicants(@PathVariable Short faculty_id, HttpServletRequest request) {
        Faculty faculty = facultyService.readById(faculty_id);
        logger.info("User wants to see the list of applicants to the faculty = "  + faculty + ".");
        List<Application> applications = applicationService.getApplicationsByFacultyAndConfirmed(faculty, true);
        List<ApplicationDTO> applicationDTOS = new ArrayList<>();
        int place = 1;
        for(Application application : applications) {
            applicationDTOS.add(new ApplicationDTO(application, place));
            place++;
        }
        request.setAttribute("user", (User) request.getSession().getAttribute("user"));
        request.setAttribute("applicationDTOS", applicationDTOS);
        request.setAttribute("faculty", faculty);
        return "list-of-applicants";
    }

    @GetMapping(value = "/acceptApplication")
    public String acceptApplication(@RequestParam("id") Integer id) {
        logger.info("Admin is confirming the application with the id = " + id + ".");
        Application application = applicationService.findById(id);
        application.setConfirmed(true);
        applicationService.update(application);
        logger.info("The confirmation is successful.");
        return "redirect:/home";
    }

    @GetMapping(value = "/deleteApplication")
    public String deleteApplication(@RequestParam("id") Integer id) {
        logger.info("Admin is declining the application with the id = " + id + ".");
        Application application = applicationService.findById(id);
        applicationService.delete(application);
        logger.info("Application was deleted.");
        return "redirect:/home";
    }
}
