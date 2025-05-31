package project.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.domain.Faculty;
import project.domain.Speciality;
import project.domain.User;
import project.service.DepartmentService;
import project.service.FacultyService;
import project.service.SpecialityService;
import project.service.UniversityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SpecialityController {
    @Autowired
    SpecialityService specialityService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    FacultyService facultyService;

    @Autowired
    UniversityService universityService;

    Logger logger = LoggerFactory.getLogger(SpecialityController.class);

    @GetMapping(value="/specialities")
    public String specialities(@RequestParam(required = false, name="department_id") Integer departmentId, HttpServletRequest request) {
        List<Speciality> specialities = departmentId == null ? specialityService.readAll() : specialityService.readAllByDepartment(departmentService.readById(departmentId));
        request.setAttribute("specialities", specialities);
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("role", user.getRole().toString());
        return "specialities";
    }

    @GetMapping(value="/specialities/{speciality_id}")
    public String speciality(@PathVariable Integer speciality_id, HttpServletRequest request) {
        request.setAttribute("speciality", specialityService.readById(speciality_id));
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("role", user.getRole().toString());
        return "speciality";
    }

    @GetMapping(value = "/addSpeciality")
    public String addSpeciality(HttpServletRequest request) {
        logger.info("Admin is on the addSpeciality page.");
        request.setAttribute("specialityForm", new Speciality());
        request.setAttribute("universities", universityService.readAll());
        request.setAttribute("faculties", facultyService.readAll());
        request.setAttribute("departments", departmentService.readAll());
        Gson gson = new Gson();
        request.setAttribute("facultiesJson", gson.toJson(facultyService.readAll()));
        request.setAttribute("departmentsJson", gson.toJson(departmentService.readAll()));
        request.setAttribute("mode", "MODE_ADD");
        return "specialityForm";
    }

    @PostMapping(value = "/addSpeciality")
    public String addSpeciality(@ModelAttribute("specialityForm") Speciality speciality, BindingResult bindingResult, @RequestParam("departmentId") Integer department_id, HttpServletRequest request) {
        logger.info("Admin is trying to add a new speciality.");
        if(bindingResult.hasErrors()) {
            logger.info("The speciality wasn't added. There were some errors.");
            return "specialityForm";
        }
        speciality.setDepartment(departmentService.readById(department_id));
        if(specialityService.save(speciality)) {
            logger.info("Admin successfully added a new speciality.");
            return "redirect:/specialities?department_id=" + department_id;
        }
        request.setAttribute("msg", "The speciality  with the name " + speciality.getName() + " is already exists or check the coefficients, their sum should equal to 1.");
        logger.info("The speciality wasn't added.");
        return "specialityForm";
    }

    @GetMapping(value = "/updateSpeciality")
    public String updateSpeciality(@RequestParam Integer id, HttpServletRequest request) {
        logger.info("Admin is on the update page.");
        request.setAttribute("specialityForm", specialityService.readById(id));
        request.setAttribute("universities", universityService.readAll());
        request.setAttribute("faculties", facultyService.readAll());
        request.setAttribute("departments", departmentService.readAll());
        Gson gson = new Gson();
        request.setAttribute("facultiesJson", gson.toJson(facultyService.readAll()));
        request.setAttribute("departmentsJson", gson.toJson(departmentService.readAll()));
        request.setAttribute("mode", "MODE_UPDATE");
        return "specialityForm";
    }

    @PostMapping(value = "/updateSpeciality")
    public String updateSpeciality(@ModelAttribute("specialityForm") Speciality speciality, BindingResult bindingResult, @RequestParam("departmentId") Integer departmentId) {
        logger.info("Admin is updating the speciality.");
        if(bindingResult.hasErrors()) {
            logger.info("The speciality wasn't updated. There were some errors.");
            return "specialityForm";
        }
        speciality.setDepartment(departmentService.readById(departmentId));
        specialityService.update(speciality);
        logger.info("The faculty was successfully updated.");
        return "redirect:/specialities?department_id=" + departmentId;
    }

    @GetMapping(value = "/deleteSpeciality")
    public String deleteSpeciality(@RequestParam Integer id) {
        logger.info("Admin is deleting the speciality with id = " + id + ".");
        Speciality speciality = specialityService.readById(id);
        specialityService.delete(speciality);
        return "redirect:/specialities?department_id=" + speciality.getDepartment().getId();
    }
}
