package project.controller;

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

    @RequestMapping(value="/application", method = RequestMethod.GET)
    public String application(Model model) {
        model.addAttribute("faculties", facultyService.readAll());
        model.addAttribute("applicationForm", new Application());
        return "application";
    }

    @RequestMapping(value = "/application", method = RequestMethod.POST)
    public String application(@ModelAttribute("applicationForm") Application applicationForm, BindingResult bindingResult, @RequestParam("facultyId") Integer facultyId, HttpServletRequest request){
        if(bindingResult.hasErrors()) {
            return "aplication";
        }
        User user = (User) request.getSession().getAttribute("user");
        applicationForm.setFacultyID(facultyId);
        user = userService.getUserByEmail(user.getEmail());
        applicationService.save(applicationForm, user);
        return "redirect:/home";
    }

    @RequestMapping(value = "/listOfApplicants", method = RequestMethod.GET)
    public String listOfApplicants(@RequestParam("id") Integer id, Model model) {
        List<Application> applications = applicationService.getApplicationsByFaculty(id, 1);
        List<ApplicationModel> applicationModels = new ArrayList<>();
        int place = 1;
        for(Application application : applications) {
            ApplicationModel applicationModel = new ApplicationModel();
            User user = userService.findById(application.getApplicantId());
            applicationModel.setPlace(place);
            applicationModel.setApplicantName(user.getName());
            applicationModel.setApplicantSurname(user.getSurname());
            applicationModel.setMathsMark(application.getMathsMark());
            applicationModel.setEnglishMark(application.getEnglishMark());
            applicationModel.setPhysicsMark(application.getPhysicsMark());
            applicationModel.setCertificateMark(application.getCertificateMark());
            applicationModel.setRatingMark(application.getRatingMark());
            applicationModels.add(applicationModel);
            place++;
        }
        model.addAttribute("applications", applicationModels);
        model.addAttribute("faculty", facultyService.readById(id));
        return "listOfApplicants";
    }
}
