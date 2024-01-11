package project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private FacultyService facultyService;

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(FacultyController.class);

    @RequestMapping(value = "/addFaculty", method = RequestMethod.GET)
    public String addFaculty(Model model) {
        logger.info("Admin is on the addFaculty page.");
        model.addAttribute("facultyForm", new Faculty());
        return "addFaculty";
    }

    @RequestMapping(value = "/addFaculty", method = RequestMethod.POST)
    public String addFaculty(@ModelAttribute("facultyForm") Faculty faculty, BindingResult bindingResult, Model model) {
        logger.info("Admin is trying to add a new faculty.");
        if(bindingResult.hasErrors()) {
            logger.info("The faculty wasn't added. There were some errors.");
            return "addFaculty";
        }
        if(facultyService.save(faculty)) {
            logger.info("Admin successfully added a new faculty.");
            return "redirect:/adminFaculties";
        }
        model.addAttribute("msg", "The faculty  with the name " + faculty.getName() + " is already exists");
        logger.info("The faculty wasn't added.");
        return "addFaculty";
    }

    @RequestMapping(value = "/adminFaculties", method = RequestMethod.GET)
    public String adminFaculties(HttpServletRequest request) {
        logger.info("Admin is on the adminFaculties page.");
        request.setAttribute("faculties", facultyService.readAll());
        return "adminFaculties";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam Integer id, Model model) {
        logger.info("Admin is on the update page.");
        model.addAttribute("facultyForm", facultyService.readById(id));
        return "updateFaculty";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("facultyForm") Faculty faculty, BindingResult bindingResult) {
        logger.info("Admin is updating the faculty.");
        if(bindingResult.hasErrors()) {
            logger.info("The faculty wasn't updated. There were some errors.");
            return "updateFaculty";
        }
        facultyService.update(faculty);
        logger.info("The faculty was successfully updated.");
        return "redirect:/adminFaculties";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam Integer id) {
        logger.info("Admin is deleting the faculty with id = " + id + ".");
        facultyService.delete(id);
        return "redirect:/adminFaculties";
    }
    @RequestMapping(value="userFaculties", method = RequestMethod.GET)
    public String userFaculties(HttpServletRequest request) {
        logger.info("The user is on the userFaculties page.");
        request.setAttribute("user", (User) request.getSession().getAttribute("user"));
        request.setAttribute("faculties", facultyService.readAll());
        return "userFaculties";
    }
}
