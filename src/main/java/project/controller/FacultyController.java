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
import project.service.UniversityService;
import project.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private UniversityService universityService;

    Logger logger = LoggerFactory.getLogger(FacultyController.class);

    @GetMapping(value="/faculties")
    public String faculties(@RequestParam("university_id") Integer university_id, HttpServletRequest request) {
        request.setAttribute("faculties", facultyService.readAllByUniversity(universityService.readById(university_id)));
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("role", user.getRole().toString());
        return "faculties";
    }

    @GetMapping(value="/faculties/{faculty_id}")
    public String faculty(@PathVariable Integer faculty_id, HttpServletRequest request) {
        request.setAttribute("faculty", facultyService.readById(faculty_id));
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("role", user.getRole().toString());
        return "faculty";
    }

    @GetMapping(value = "/addFaculty")
    public String addFaculty(HttpServletRequest request) {
        logger.info("Admin is on the addFaculty page.");
        request.setAttribute("facultyForm", new Faculty());
        request.setAttribute("universities", universityService.readAll());
        request.setAttribute("mode", "MODE_ADD");
        return "facultyForm";
    }

    @PostMapping(value = "/addFaculty")
    public String addFaculty(@ModelAttribute("facultyForm") Faculty faculty, BindingResult bindingResult, @RequestParam("universityId") Integer universityId, HttpServletRequest request) {
        logger.info("Admin is trying to add a new faculty.");
        if(bindingResult.hasErrors()) {
            logger.info("The faculty wasn't added. There were some errors.");
            return "facultyForm";
        }
        faculty.setUniversity(universityService.readById(universityId));
        if(facultyService.save(faculty)) {
            logger.info("Admin successfully added a new faculty.");
            return "redirect:/faculties?university_id=" + universityId;
        }
        request.setAttribute("msg", "The faculty  with the name " + faculty.getName() + " is already exists");
        logger.info("The faculty wasn't added.");
        return "facultyForm";
    }

    @GetMapping(value = "/updateFaculty")
    public String updateFaculty(@RequestParam Integer id, HttpServletRequest request) {
        logger.info("Admin is on the update page.");
        request.setAttribute("facultyForm", facultyService.readById(id));
        request.setAttribute("universities", universityService.readAll());
        request.setAttribute("mode", "MODE_UPDATE");
        return "facultyForm";
    }

    @PostMapping(value = "/updateFaculty")
    public String updateFaculty(@ModelAttribute("facultyForm") Faculty faculty, BindingResult bindingResult, @RequestParam("universityId") Integer universityId) {
        logger.info("Admin is updating the faculty.");
        if(bindingResult.hasErrors()) {
            logger.info("The faculty wasn't updated. There were some errors.");
            return "facultyForm";
        }
        faculty.setUniversity(universityService.readById(universityId));
        facultyService.update(faculty);
        logger.info("The faculty was successfully updated.");
        return "redirect:/faculties?university_id=" + universityId;
    }

    @GetMapping(value = "/deleteFaculty")
    public String deleteFaculty(@RequestParam Integer id) {
        logger.info("Admin is deleting the faculty with id = " + id + ".");
        Faculty faculty = facultyService.readById(id);
        facultyService.delete(faculty);
        return "redirect:/adminFaculties?university_id=" + faculty.getUniversity().getId();
    }
}
