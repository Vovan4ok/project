package project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import project.service.ApplicationModelDTOHelper;
import project.service.ApplicationService;
import project.service.FacultyService;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationController {
    @Autowired
    private FacultyService facultyService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @RequestMapping(value="/application", method = RequestMethod.GET)
    public String application(Model model, HttpServletRequest request) {
        logger.info("The user is on the application page.");
        request.setAttribute("user", (User) request.getSession().getAttribute("user"));
        model.addAttribute("faculties", facultyService.readAll());
        model.addAttribute("applicationForm", new Application());
        return "application";
    }

    @RequestMapping(value = "/application", method = RequestMethod.POST)
    public String application(@ModelAttribute("applicationForm") Application applicationForm, BindingResult bindingResult, @RequestParam("facultyId") Integer facultyId, HttpServletRequest request){
        logger.info("User is trying to apply.");
        if(bindingResult.hasErrors()) {
            logger.info("The application wasn't successful.");
            return "aplication";
        }
        User user = (User) request.getSession().getAttribute("user");
        applicationForm.setFacultyID(facultyId);
        user = userService.getUserByEmail(user.getEmail());
        applicationService.save(applicationForm, user);
        logger.info("User made an application");
        return "redirect:/home";
    }

    @RequestMapping(value = "/listOfApplicants", method = RequestMethod.GET)
    public String listOfApplicants(@RequestParam("id") Integer id, Model model, HttpServletRequest request) {
        logger.info("User wants to see the list of applicants to the faculty with id = "  + id + ".");
        List<Application> applications = applicationService.getApplicationsByFaculty(id, 1);
        List<ApplicationModel> applicationModels = new ArrayList<>();
        int place = 1;
        for(Application application : applications) {
            User applicant = userService.findById(application.getApplicantId());

            applicationModels.add(ApplicationModelDTOHelper.createEntityForUser(
                    place,
                    applicant.getName(),
                    applicant.getSurname(),
                    application.getMathsMark(),
                    application.getEnglishMark(),
                    application.getPhysicsMark(),
                    application.getCertificateMark(),
                    application.getRatingMark()
            ));
            place++;
        }
        request.setAttribute("user", (User) request.getSession().getAttribute("user"));
        model.addAttribute("applications", applicationModels);
        model.addAttribute("faculty", facultyService.readById(id));
        return "listOfApplicants";
    }
}
