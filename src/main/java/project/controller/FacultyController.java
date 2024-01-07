package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.domain.Faculty;
import project.domain.User;
import project.service.FacultyService;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;

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
            return "redirect:/adminFaculties";
        }
        model.addAttribute("msg", "The faculty  with the name " + faculty.getName() + " is already exists");
        return "addFaculty";
    }

    @RequestMapping(value = "/adminFaculties", method = RequestMethod.GET)
    public String adminFaculties(HttpServletRequest request) {
        request.setAttribute("faculties", facultyService.readAll());
        return "adminFaculties";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam Integer id, Model model) {
        model.addAttribute("facultyForm", facultyService.readById(id));
        return "updateFaculty";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("facultyForm") Faculty faculty, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "updateFaculty";
        }
        facultyService.update(faculty);
        return "redirect:/adminFaculties";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam Integer id) {
        facultyService.delete(id);
        return "redirect:/adminFaculties";
    }
}
