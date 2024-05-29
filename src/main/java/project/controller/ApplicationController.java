package project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.domain.*;
import project.dto.ApplicationDTO;
import project.dto.ApplicationDTOHelper;
import project.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserService userService;

    @Autowired
    private SpecialityService specialityService;

    Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @GetMapping(value="/makeApplication")
    public String makeApplication(@RequestParam(required = false, name = "speciality_id") Integer specialityId, HttpServletRequest request) {
        logger.info("The user is on the application page.");
        request.setAttribute("user", (User) request.getSession().getAttribute("user"));
        request.setAttribute("specialities", specialityService.readAll());
        if(specialityId != null) {
            request.setAttribute("specialityId", specialityId);
        }
        return "applicationForm";
    }

    @PostMapping(value = "/makeApplication")
    public String makeApplication(@ModelAttribute("applicationForm") Application applicationForm, BindingResult bindingResult, @RequestParam("specialityId") Integer specialityId, HttpServletRequest request){
        logger.info("User is trying to apply.");
        if(bindingResult.hasErrors()) {
            logger.info("The application wasn't successful.");
            return "applicationForm";
        }
        User user = (User) request.getSession().getAttribute("user");
        applicationForm.setSpeciality(specialityService.readById(specialityId));
        user = userService.getUserByEmail(user.getEmail());
        if(applicationService.save(applicationForm, user)) {
            logger.info("User made an application");
            return "redirect:/home";
        }
        request.setAttribute("msg", "You've already applied to this speciality");
        return "applicationForm";
    }

    @GetMapping(value = "/listOfApplicants")
    public String listOfApplicants(@RequestParam Integer speciality_id, HttpServletRequest request) {
        Speciality speciality = specialityService.readById(speciality_id);
        logger.info("User wants to see the list of applicants to the speciality = "  + speciality + ".");
        List<ApplicationDTO> applicationDTOS = ApplicationDTOHelper.createList(applicationService.getApplicationsBySpeciality(speciality));
        request.setAttribute("user", (User) request.getSession().getAttribute("user"));
        request.setAttribute("applicationDTOS", applicationDTOS);
        request.setAttribute("speciality", speciality);
        return "listOfApplicants";
    }

    @GetMapping(value = "/acceptApplication")
    public String acceptApplication(@RequestParam("id") Integer id) {
        logger.info("Admin is confirming the application with the id = " + id + ".");
        Application application = applicationService.findById(id);
        application.setStatus(Status.ACCEPTED);
        applicationService.update(application);
        logger.info("The confirmation is successful.");
        return "redirect:/home";
    }

    @GetMapping(value = "/declineApplication")
    public String declineApplication(@RequestParam("id") Integer id) {
        logger.info("Admin is declining the application with the id = " + id + ".");
        Application application = applicationService.findById(id);
        application.setStatus(Status.DECLINED);
        applicationService.update(application);
        logger.info("Application was deleted.");
        return "redirect:/home";
    }

    @GetMapping(value="/applicationHistory")
    public String getHistory(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("applications", applicationService.readAllByApplicant(user));
        return "applicationHistory";
    }

    @GetMapping(value="/deleteApplication")
    public String deleteApplication(@RequestParam(name="application_id") Integer applicationId, HttpServletRequest request) {
        applicationService.delete(applicationService.findById(applicationId));
        return "redirect:/applicationHistory";
    }
}
