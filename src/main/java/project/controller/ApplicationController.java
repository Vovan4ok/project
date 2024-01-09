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
import project.domain.User;
import project.service.ApplicationService;
import project.service.FacultyService;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;

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
            return "application";
        }
        User user = (User) request.getSession().getAttribute("user");
        applicationForm.setFacultyID(facultyId);
        user = userService.getUserByEmail(user.getEmail());
        applicationService.save(applicationForm, user);
        return "redirect:/home";
    }
}
