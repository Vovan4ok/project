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

    Logger logger = LoggerFactory.getLogger(FacultyController.class);

    @GetMapping(value="/faculties")
    public String faculties(HttpServletRequest request) {
        request.setAttribute("faculties", facultyService.readAll());
        return "faculties";
    }

    @GetMapping(value="/faculty")
    public String faculty(@RequestParam Short id, HttpServletRequest request) {
        request.setAttribute("faculty", facultyService.readById(id));
        return "faculty";
    }

    @GetMapping(value = "/addFaculty")
    public String addFaculty(HttpServletRequest request) {
        logger.info("Admin is on the addFaculty page.");
        request.setAttribute("facultyForm", new Faculty());
        return "addFaculty";
    }

    @PostMapping(value = "/addFaculty")
    public String addFaculty(@ModelAttribute("facultyForm") Faculty faculty, BindingResult bindingResult, HttpServletRequest request) {
        logger.info("Admin is trying to add a new faculty.");
        if(bindingResult.hasErrors()) {
            logger.info("The faculty wasn't added. There were some errors.");
            return "addFaculty";
        }
        if(facultyService.save(faculty)) {
            logger.info("Admin successfully added a new faculty.");
            return "redirect:/adminFaculties";
        }
        request.setAttribute("msg", "The faculty  with the name " + faculty.getName() + " is already exists");
        logger.info("The faculty wasn't added.");
        return "addFaculty";
    }

    @GetMapping(value = "/adminFaculties")
    public String adminFaculties(HttpServletRequest request) {
        logger.info("Admin is on the adminFaculties page.");
        request.setAttribute("faculties", facultyService.readAll());
        return "adminFaculties";
    }

    @GetMapping(value = "/update")
    public String update(@RequestParam Short id, HttpServletRequest request) {
        logger.info("Admin is on the update page.");
        request.setAttribute("facultyForm", facultyService.readById(id));
        return "updateFaculty";
    }

    @PostMapping(value = "/update")
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

    @GetMapping(value = "/delete")
    public String delete(@RequestParam Short id) {
        logger.info("Admin is deleting the faculty with id = " + id + ".");
        Faculty faculty = facultyService.readById(id);
        facultyService.delete(faculty);
        return "redirect:/adminFaculties";
    }
    @GetMapping(value="userFaculties")
    public String userFaculties(HttpServletRequest request) {
        logger.info("The user is on the userFaculties page.");
        request.setAttribute("user", (User) request.getSession().getAttribute("user"));
        request.setAttribute("faculties", facultyService.readAll());
        return "userFaculties";
    }
}
