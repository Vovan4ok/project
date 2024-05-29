package project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.domain.University;
import project.domain.User;
import project.service.UniversityService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UniversityController {
    @Autowired
    UniversityService universityService;

    Logger logger = LoggerFactory.getLogger(UniversityController.class);

    @GetMapping(value="/universities")
    public String universities(HttpServletRequest request) {
        request.setAttribute("universities", universityService.readAll());
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("role", user.getRole().toString());
        return "universities";
    }

    @GetMapping(value="/universities/{university_id}")
    public String university(@PathVariable Integer university_id, HttpServletRequest request) {
        request.setAttribute("university", universityService.readById(university_id));
        User user = (User) request.getSession().getAttribute("user");
        logger.info("User " + user);
        request.setAttribute("role", user.getRole().toString());
        return "university";
    }

    @GetMapping(value="/addUniversity")
    public String addUniversity(HttpServletRequest request) {
        logger.info("Admin is on the addUniveristy page");
        request.setAttribute("universityForm", new University());
        request.setAttribute("mode", "MODE_ADD");
        return "universityForm";
    }

    @PostMapping(value="addUniversity")
    public String addUniversity(@ModelAttribute("universityForm") University university, BindingResult bindingResult, HttpServletRequest request) {
        logger.info("Admin is trying to add a new university");
        if(bindingResult.hasErrors()) {
            logger.info("The university wasn't saved. There were some errors.");
            return "universityForm";
        }
        if(universityService.save(university)) {
            logger.info("Admin successfully added a new university");
            return "redirect:/universities";
        }
        return "universityForm";
    }

    @GetMapping(value="/updateUniversity")
    public String updateUniversity(@RequestParam Integer id, HttpServletRequest request) {
        logger.info("Admin is on the updateUniveristy page");
        request.setAttribute("universityForm", universityService.readById(id));
        request.setAttribute("mode", "MODE_UPDATE");
        return "universityForm";
    }

    @PostMapping(value="updateUniversity")
    public String updateUniversity(@ModelAttribute("universityForm") University university, BindingResult bindingResult, HttpServletRequest request) {
        logger.info("Admin is trying to update the university");
        if(bindingResult.hasErrors()) {
            logger.info("The university wasn't updated. There were some errors.");
            return "universityForm";
        }
        universityService.update(university);
        logger.info("The university was successfully updated");
        return "redirect:/universities";
    }

    @GetMapping(value="/deleteUniversity")
    public String deleteUniversity(@RequestParam Integer id) {
        logger.info("Admin is deleting the university with id = " + id + ".");
        University university = universityService.readById(id);
        universityService.delete(university);
        return "redirect:/universities";
    }
}
