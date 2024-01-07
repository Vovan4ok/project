package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.domain.Faculty;
import project.domain.User;
import project.service.FacultyService;
import project.service.UserService;

@Controller
public class FacultyController {
    @Autowired
    FacultyService facultyService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/addFaculty", method = RequestMethod.GET)
    public String addFaculty(Model model) {
        model.addAttribute("facultyForm", new Faculty());
        return "addFaculty";
    }

    @RequestMapping(value = "/addFaculty", method = RequestMethod.POST)
    public String addFaculty(@ModelAttribute("facultyForm") Faculty faculty, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "addFaculty";
        }
        if(facultyService.save(faculty)) {
            return "redirect:/home";
        }
        model.addAttribute("msg", "The faculty  with the name " + faculty.getName() + " is already exists");
        return "addFaculty";
    }
}
